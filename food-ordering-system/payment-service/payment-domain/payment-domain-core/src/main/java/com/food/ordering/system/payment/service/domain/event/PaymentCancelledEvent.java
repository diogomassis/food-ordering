package com.food.ordering.system.payment.service.domain.event;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.Collections;

public class PaymentCancelledEvent extends PaymentEvent {
    private final IDomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher;

    public PaymentCancelledEvent(Payment payment, ZonedDateTime createdAt,
            IDomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher) {
        super(payment, createdAt, Collections.emptyList());
        this.paymentCancelledEventDomainEventPublisher = paymentCancelledEventDomainEventPublisher;
    }
}
