package com.food.ordering.system.payment.service.domain.event;

import java.time.ZonedDateTime;
import java.util.Collections;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

/**
 * Domain event representing a completed payment in the payment service.
 * This event is fired when a payment has been successfully completed and
 * contains
 * the payment information along with the event publisher for notification.
 */
public class PaymentCompletedEvent extends PaymentEvent {
    /**
     * The domain event publisher responsible for publishing this payment completed
     * event.
     */
    private final IDomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher;

    /**
     * Constructs a new PaymentCompletedEvent with the specified payment, creation
     * timestamp, and event publisher.
     *
     * @param payment                                   the payment entity that was
     *                                                  completed
     * @param createdAt                                 the timestamp when this
     *                                                  event was created
     * @param paymentCompletedEventDomainEventPublisher the publisher for this
     *                                                  completed payment event
     */
    public PaymentCompletedEvent(Payment payment, ZonedDateTime createdAt,
            IDomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher) {
        super(payment, createdAt, Collections.emptyList());
        this.paymentCompletedEventDomainEventPublisher = paymentCompletedEventDomainEventPublisher;
    }
}
