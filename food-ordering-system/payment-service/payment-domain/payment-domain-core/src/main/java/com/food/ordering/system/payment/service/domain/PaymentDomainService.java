package com.food.ordering.system.payment.service.domain;

import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.PaymentStatus;
import com.food.ordering.system.payment.service.domain.entity.CreditEntry;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentFailedEvent;
import com.food.ordering.system.payment.service.domain.valueobject.CreditHistoryId;
import com.food.ordering.system.payment.service.domain.valueobject.TransactionType;

import static com.food.ordering.system.domain.DomainConstants.UTC;

/**
 * Implementation of the payment domain service that provides core business
 * logic
 * for payment processing operations including validation, initiation, and
 * cancellation.
 * This service orchestrates payment workflows and manages credit operations.
 */
@Slf4j
public class PaymentDomainService implements IPaymentDomainService {
    @Override
    public PaymentEvent validateAndInitiatePayment(Payment payment, CreditEntry creditEntry,
            List<CreditHistory> creditHistories, List<String> failureMessages,
            IDomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher,
            IDomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher) {
        payment.validatePayment(failureMessages);
        payment.initializePayment();
        validateCreditEntry(payment, creditEntry, failureMessages);
        subtractCreditEntry(payment, creditEntry);
        updateCreditHistory(payment, creditHistories, TransactionType.DEBIT);
        validateCreditHistory(creditEntry, creditHistories, failureMessages);

        if (failureMessages.isEmpty()) {
            log.info("Payment is initiated for order id: {}", payment.getOrderId().getValue());
            payment.updateStatus(PaymentStatus.COMPLETED);
            return new PaymentCompletedEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)),
                    paymentCompletedEventDomainEventPublisher);
        } else {
            log.info("Payment initiation is failed for order id: {}", payment.getOrderId().getValue());
            payment.updateStatus(PaymentStatus.FAILED);
            return new PaymentFailedEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)), failureMessages,
                    paymentFailedEventDomainEventPublisher);
        }
    }

    @Override
    public PaymentEvent validateAndCancelPayment(Payment payment, CreditEntry creditEntry,
            List<CreditHistory> creditHistories, List<String> failureMessages,
            IDomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
            IDomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher) {
        payment.validatePayment(failureMessages);
        addCreditEntry(payment, creditEntry);
        updateCreditHistory(payment, creditHistories, TransactionType.CREDIT);

        if (failureMessages.isEmpty()) {
            log.info("Payment is cancelled for order id: {}", payment.getOrderId().getValue());
            payment.updateStatus(PaymentStatus.CANCELLED);
            return new PaymentCancelledEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)),
                    paymentCancelledEventDomainEventPublisher);
        } else {
            log.info("Payment cancellation is failed for order id: {}", payment.getOrderId().getValue());
            payment.updateStatus(PaymentStatus.FAILED);
            return new PaymentFailedEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)), failureMessages,
                    paymentFailedEventDomainEventPublisher);
        }
    }

    /**
     * Validates that the customer has sufficient credit to complete the payment.
     * Checks if the payment amount is greater than the available credit amount
     * and adds a failure message if insufficient credit is available.
     *
     * @param payment         the payment to validate
     * @param creditEntry     the customer's credit entry containing available
     *                        credit
     * @param failureMessages list to add validation failure messages to
     */
    private void validateCreditEntry(Payment payment, CreditEntry creditEntry, List<String> failureMessages) {
        if (payment.getPrice().isGreaterThan(creditEntry.getTotalCreditAmount())) {
            log.error("Customer with id: {} doesn't have enough credit for payment!",
                    payment.getCustomerId().getValue());
            failureMessages.add("Customer with id=" + payment.getCustomerId().getValue()
                    + " doesn't have enough credit for payment!");
        }
    }

    /**
     * Subtracts the payment amount from the customer's credit entry.
     * This operation reduces the available credit by the payment amount.
     *
     * @param payment     the payment containing the amount to subtract
     * @param creditEntry the customer's credit entry to update
     */
    private void subtractCreditEntry(Payment payment, CreditEntry creditEntry) {
        creditEntry.subtractCreditAmount(payment.getPrice());
    }

    /**
     * Updates the credit history by adding a new transaction record.
     * Creates a new credit history entry with the payment details and specified
     * transaction type.
     *
     * @param payment         the payment to record in history
     * @param creditHistories the list of credit history entries to update
     * @param transactionType the type of transaction (CREDIT or DEBIT)
     */
    private void updateCreditHistory(Payment payment,
            List<CreditHistory> creditHistories,
            TransactionType transactionType) {
        creditHistories.add(CreditHistory.builder()
                .creditHistoryId(new CreditHistoryId(UUID.randomUUID()))
                .customerId(payment.getCustomerId())
                .amount(payment.getPrice())
                .transactionType(transactionType)
                .build());
    }

    /**
     * Validates the credit history consistency and ensures data integrity.
     * Checks that total debits don't exceed total credits and that the current
     * credit amount matches the calculated history balance.
     *
     * @param creditEntry     the customer's credit entry to validate against
     * @param creditHistories the list of credit history entries to validate
     * @param failureMessages list to add validation failure messages to
     */
    private void validateCreditHistory(CreditEntry creditEntry,
            List<CreditHistory> creditHistories,
            List<String> failureMessages) {
        Money totalCreditHistory = getTotalHistoryAmount(creditHistories, TransactionType.CREDIT);
        Money totalDebitHistory = getTotalHistoryAmount(creditHistories, TransactionType.DEBIT);

        if (totalDebitHistory.isGreaterThan(totalCreditHistory)) {
            log.error("Customer with id: {} doesn't have enough credit according to credit history",
                    creditEntry.getCustomerId().getValue());
            failureMessages.add("Customer with id=" + creditEntry.getCustomerId().getValue() +
                    " doesn't have enough credit according to credit history!");
        }

        if (!creditEntry.getTotalCreditAmount().equals(totalCreditHistory.subtract(totalDebitHistory))) {
            log.error("Credit history total is not equal to current credit for customer id: {}!",
                    creditEntry.getCustomerId().getValue());
            failureMessages.add("Credit history total is not equal to current credit for customer id: {}" +
                    creditEntry.getCustomerId().getValue() + "!");
        }
    }

    /**
     * Calculates the total amount for a specific transaction type from credit
     * history.
     * Filters credit history entries by transaction type and sums their amounts.
     *
     * @param creditHistories the list of credit history entries to process
     * @param transactionType the transaction type to filter by (CREDIT or DEBIT)
     * @return the total amount for the specified transaction type
     */
    private Money getTotalHistoryAmount(List<CreditHistory> creditHistories, TransactionType transactionType) {
        return creditHistories.stream()
                .filter(creditHistory -> transactionType == creditHistory.getTransactionType())
                .map(CreditHistory::getAmount)
                .reduce(Money.ZERO, Money::add);
    }

    /**
     * Adds the payment amount to the customer's credit entry.
     * This operation increases the available credit by the payment amount,
     * typically used during payment cancellation to refund the credit.
     *
     * @param payment     the payment containing the amount to add back
     * @param creditEntry the customer's credit entry to update
     */
    private void addCreditEntry(Payment payment, CreditEntry creditEntry) {
        creditEntry.addCreditAmount(payment.getPrice());
    }
}
