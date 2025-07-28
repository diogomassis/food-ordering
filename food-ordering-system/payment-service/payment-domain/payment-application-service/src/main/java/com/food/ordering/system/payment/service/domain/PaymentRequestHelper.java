package com.food.ordering.system.payment.service.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.food.ordering.system.payment.service.domain.entity.CreditEntry;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.food.ordering.system.payment.service.domain.exception.PaymentApplicationServiceException;
import com.food.ordering.system.payment.service.domain.mapper.PaymentDataMapper;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.IPaymentCancelledMessagePublisher;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.IPaymentCompletedMessagePublisher;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.IPaymentFailedMessagePublisher;
import com.food.ordering.system.payment.service.domain.ports.output.repository.ICreditEntryRepository;
import com.food.ordering.system.payment.service.domain.ports.output.repository.ICreditHistoryRepository;
import com.food.ordering.system.payment.service.domain.ports.output.repository.IPaymentRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Helper class for processing payment requests, including payment completion
 * and cancellation.
 * Handles persistence and validation logic for payment operations.
 */
@Slf4j
@Component
public class PaymentRequestHelper {
    /**
     * Domain service for payment operations.
     */
    private final PaymentDomainService paymentDomainService;

    /**
     * Mapper for converting payment request models.
     */
    private final PaymentDataMapper paymentDataMapper;

    /**
     * Repository for payment entities.
     */
    private final IPaymentRepository paymentRepository;

    /**
     * Repository for credit entry entities.
     */
    private final ICreditEntryRepository creditEntryRepository;

    /**
     * Repository for credit history entities.
     */
    private final ICreditHistoryRepository creditHistoryRepository;

    /**
     * Publisher for completed payment events.
     */
    private final IPaymentCompletedMessagePublisher paymentCompletedEventDomainEventPublisher;

    /**
     * Publisher for cancelled payment events.
     */
    private final IPaymentCancelledMessagePublisher paymentCancelledEventDomainEventPublisher;

    /**
     * Publisher for failed payment events.
     */
    private final IPaymentFailedMessagePublisher paymentFailedEventDomainEventPublisher;

    /**
     * Constructs a new PaymentRequestHelper with required dependencies.
     *
     * @param paymentDomainService                      Domain service for payment
     *                                                  operations
     * @param paymentDataMapper                         Mapper for payment data
     * @param paymentRepository                         Repository for payments
     * @param creditEntryRepository                     Repository for credit
     *                                                  entries
     * @param creditHistoryRepository                   Repository for credit
     *                                                  histories
     * @param paymentCompletedEventDomainEventPublisher Publisher for completed
     *                                                  payment events
     * @param paymentCancelledEventDomainEventPublisher Publisher for cancelled
     *                                                  payment events
     * @param paymentFailedEventDomainEventPublisher    Publisher for failed payment
     *                                                  events
     */
    public PaymentRequestHelper(PaymentDomainService paymentDomainService,
            PaymentDataMapper paymentDataMapper,
            IPaymentRepository paymentRepository,
            ICreditEntryRepository creditEntryRepository,
            ICreditHistoryRepository creditHistoryRepository,
            IPaymentCompletedMessagePublisher paymentCompletedEventDomainEventPublisher,
            IPaymentCancelledMessagePublisher paymentCancelledEventDomainEventPublisher,
            IPaymentFailedMessagePublisher paymentFailedEventDomainEventPublisher) {
        this.paymentDomainService = paymentDomainService;
        this.paymentDataMapper = paymentDataMapper;
        this.paymentRepository = paymentRepository;
        this.creditEntryRepository = creditEntryRepository;
        this.creditHistoryRepository = creditHistoryRepository;
        this.paymentCompletedEventDomainEventPublisher = paymentCompletedEventDomainEventPublisher;
        this.paymentCancelledEventDomainEventPublisher = paymentCancelledEventDomainEventPublisher;
        this.paymentFailedEventDomainEventPublisher = paymentFailedEventDomainEventPublisher;
    }

    /**
     * Persists a payment and processes payment completion logic.
     *
     * @param paymentRequest The payment request to process
     * @return The resulting PaymentEvent after processing
     */
    @Transactional
    public PaymentEvent persistPayment(PaymentRequest paymentRequest) {
        log.info("Received payment complete event for order id: {}", paymentRequest.getOrderId());
        Payment payment = paymentDataMapper.paymentRequestModelToPayment(paymentRequest);
        CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
        List<CreditHistory> creditHistories = getCreditHistory(payment.getCustomerId());
        List<String> failureMessages = new ArrayList<>();
        PaymentEvent paymentEvent = paymentDomainService.validateAndInitiatePayment(payment, creditEntry,
                creditHistories, failureMessages,
                paymentCompletedEventDomainEventPublisher, paymentFailedEventDomainEventPublisher);
        persistDbObjects(payment, creditEntry, creditHistories, failureMessages);
        return paymentEvent;
    }

    /**
     * Persists a payment cancellation and processes payment rollback logic.
     *
     * @param paymentRequest The payment request to cancel
     * @return The resulting PaymentEvent after processing cancellation
     */
    @Transactional
    public PaymentEvent persistCancelPayment(PaymentRequest paymentRequest) {
        log.info("Received payment rollback event for order id: {}", paymentRequest.getOrderId());
        Optional<Payment> paymentResponse = paymentRepository
                .findByOrderId(UUID.fromString(paymentRequest.getOrderId()));
        if (paymentResponse.isEmpty()) {
            log.error("Payment with order id: {} could not be found!", paymentRequest.getOrderId());
            throw new PaymentApplicationServiceException("Payment with order id: " +
                    paymentRequest.getOrderId() + " could not be found!");
        }
        Payment payment = paymentResponse.get();
        CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
        List<CreditHistory> creditHistories = getCreditHistory(payment.getCustomerId());
        List<String> failureMessages = new ArrayList<>();
        PaymentEvent paymentEvent = paymentDomainService
                .validateAndCancelPayment(payment, creditEntry, creditHistories, failureMessages,
                        paymentCancelledEventDomainEventPublisher, paymentFailedEventDomainEventPublisher);
        persistDbObjects(payment, creditEntry, creditHistories, failureMessages);
        return paymentEvent;
    }

    /**
     * Retrieves the credit entry for a given customer.
     *
     * @param customerId The customer ID
     * @return The CreditEntry for the customer
     * @throws PaymentApplicationServiceException if no credit entry is found
     */
    private CreditEntry getCreditEntry(CustomerId customerId) {
        Optional<CreditEntry> creditEntry = creditEntryRepository.findByCustomerId(customerId);
        if (creditEntry.isEmpty()) {
            log.error("Could not find credit entry for customer: {}", customerId.getValue());
            throw new PaymentApplicationServiceException("Could not find credit entry for customer: " +
                    customerId.getValue());
        }
        return creditEntry.get();
    }

    /**
     * Retrieves the credit history for a given customer.
     *
     * @param customerId The customer ID
     * @return The list of CreditHistory for the customer
     * @throws PaymentApplicationServiceException if no credit history is found
     */
    private List<CreditHistory> getCreditHistory(CustomerId customerId) {
        Optional<List<CreditHistory>> creditHistories = creditHistoryRepository.findByCustomerId(customerId);
        if (creditHistories.isEmpty()) {
            log.error("Could not find credit history for customer: {}", customerId.getValue());
            throw new PaymentApplicationServiceException("Could not find credit history for customer: " +
                    customerId.getValue());
        }
        return creditHistories.get();
    }

    /**
     * Persists payment, credit entry, and credit history objects to the database.
     * Only persists credit entry and history if there are no failure messages.
     *
     * @param payment         The payment entity to persist
     * @param creditEntry     The credit entry entity to persist
     * @param creditHistories The list of credit history entities to persist
     * @param failureMessages The list of failure messages
     */
    private void persistDbObjects(Payment payment,
            CreditEntry creditEntry,
            List<CreditHistory> creditHistories,
            List<String> failureMessages) {
        paymentRepository.save(payment);
        if (failureMessages.isEmpty()) {
            creditEntryRepository.save(creditEntry);
            creditHistoryRepository.save(creditHistories.get(creditHistories.size() - 1));
        }
    }
}
