package com.food.ordering.system.payment.service.domain.event;

import java.time.ZonedDateTime;
import java.util.List;

import com.food.ordering.system.domain.event.IDomainEvent;
import com.food.ordering.system.payment.service.domain.entity.Payment;

/**
 * Abstract base class for all payment-related domain events.
 * This class provides common properties and behavior for payment events
 * including the payment entity, creation timestamp, and failure messages.
 */
public abstract class PaymentEvent implements IDomainEvent<Payment> {
    /**
     * The payment entity associated with this event.
     */
    private final Payment payment;

    /**
     * The timestamp when this payment event was created, stored in UTC timezone.
     */
    private final ZonedDateTime createdAt;

    /**
     * List of failure messages associated with this payment event.
     * Empty list indicates no failures occurred.
     */
    private final List<String> failureMessages;

    /**
     * Constructs a new PaymentEvent with the specified payment, creation timestamp,
     * and failure messages.
     *
     * @param payment         the payment entity associated with this event
     * @param createdAt       the timestamp when this event was created
     * @param failureMessages list of failure messages, if any
     */
    public PaymentEvent(Payment payment, ZonedDateTime createdAt, List<String> failureMessages) {
        this.payment = payment;
        this.createdAt = createdAt;
        this.failureMessages = failureMessages;
    }

    /**
     * Gets the payment entity associated with this event.
     *
     * @return the payment entity
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Gets the timestamp when this payment event was created.
     *
     * @return the creation timestamp in UTC timezone
     */
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the list of failure messages associated with this payment event.
     *
     * @return the list of failure messages, empty if no failures occurred
     */
    public List<String> getFailureMessages() {
        return failureMessages;
    }
}
