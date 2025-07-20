package com.food.ordering.system.payment.service.domain.event;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.Collections;

/**
 * Domain event representing a cancelled payment in the payment service.
 * This event is fired when a payment has been cancelled and contains
 * the payment information along with the event publisher for notification.
 */
public class PaymentCancelledEvent extends PaymentEvent {
    /**
     * The domain event publisher responsible for publishing this payment cancelled
     * event.
     */
    private final IDomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher;

    /**
     * Constructs a new PaymentCancelledEvent with the specified payment, creation
     * timestamp, and event publisher.
     *
     * @param payment                                   the payment entity that was
     *                                                  cancelled
     * @param createdAt                                 the timestamp when this
     *                                                  event was created
     * @param paymentCancelledEventDomainEventPublisher the publisher for this
     *                                                  cancelled payment event
     */
    public PaymentCancelledEvent(Payment payment, ZonedDateTime createdAt,
            IDomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher) {
        super(payment, createdAt, Collections.emptyList());
        this.paymentCancelledEventDomainEventPublisher = paymentCancelledEventDomainEventPublisher;
    }
}
