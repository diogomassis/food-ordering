package com.food.ordering.system.payment.service.domain.event;

import java.time.ZonedDateTime;
import java.util.List;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

/**
 * Domain event representing a failed payment in the payment service.
 * This event is fired when a payment has failed and contains
 * the payment information, failure messages, and the event publisher for
 * notification.
 */
public class PaymentFailedEvent extends PaymentEvent {
    /**
     * The domain event publisher responsible for publishing this payment failed
     * event.
     */
    private final IDomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher;

    /**
     * Constructs a new PaymentFailedEvent with the specified payment, creation
     * timestamp, failure messages, and event publisher.
     *
     * @param payment                                the payment entity that failed
     * @param createdAt                              the timestamp when this event
     *                                               was created
     * @param failureMessages                        list of failure messages
     *                                               describing why the payment
     *                                               failed
     * @param paymentFailedEventDomainEventPublisher the publisher for this failed
     *                                               payment event
     */
    public PaymentFailedEvent(Payment payment, ZonedDateTime createdAt, List<String> failureMessages,
            IDomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher) {
        super(payment, createdAt, failureMessages);
        this.paymentFailedEventDomainEventPublisher = paymentFailedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        paymentFailedEventDomainEventPublisher.publish(this);
    }
}
