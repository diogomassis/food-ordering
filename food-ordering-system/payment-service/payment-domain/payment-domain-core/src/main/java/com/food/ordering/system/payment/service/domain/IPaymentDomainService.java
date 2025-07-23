package com.food.ordering.system.payment.service.domain;

import java.util.List;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.CreditEntry;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentFailedEvent;

/**
 * Domain service interface for payment-related business operations.
 * This service provides core business logic for validating and processing
 * payment transactions, including initiation and cancellation operations.
 */
public interface IPaymentDomainService {
    /**
     * Validates and initiates a payment transaction.
     * This method performs business validation on the payment, checks credit
     * availability,
     * and either completes the payment or fails it based on the validation results.
     *
     * @param payment                                   the payment entity to be
     *                                                  processed
     * @param creditEntry                               the customer's credit entry
     *                                                  for validation
     * @param creditHistories                           list of credit history
     *                                                  entries for the customer
     * @param failureMessages                           list to collect any
     *                                                  validation failure messages
     * @param paymentCompletedEventDomainEventPublisher publisher for payment
     *                                                  completed events
     * @param paymentFailedEventDomainEventPublisher    publisher for payment failed
     *                                                  events
     * @return a PaymentEvent indicating the result of the payment initiation
     *         (completed or failed)
     */
    PaymentEvent validateAndInitiatePayment(Payment payment, CreditEntry creditEntry,
            List<CreditHistory> creditHistories, List<String> failureMessages,
            IDomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher,
            IDomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);

    /**
     * Validates and cancels an existing payment transaction.
     * This method performs business validation and cancels the payment,
     * reversing any credit operations that were performed during payment
     * initiation.
     *
     * @param payment                                   the payment entity to be
     *                                                  cancelled
     * @param creditEntry                               the customer's credit entry
     *                                                  to be updated
     * @param creditHistories                           list of credit history
     *                                                  entries for the customer
     * @param failureMessages                           list to collect any
     *                                                  validation failure messages
     * @param paymentCancelledEventDomainEventPublisher publisher for payment
     *                                                  cancelled events
     * @param paymentFailedEventDomainEventPublisher    publisher for payment failed
     *                                                  events
     * @return a PaymentEvent indicating the result of the payment cancellation
     *         (cancelled or failed)
     */
    PaymentEvent validateAndCancelPayment(Payment payment, CreditEntry creditEntry,
            List<CreditHistory> creditHistories,
            List<String> failureMessages,
            IDomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
            IDomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);
}
