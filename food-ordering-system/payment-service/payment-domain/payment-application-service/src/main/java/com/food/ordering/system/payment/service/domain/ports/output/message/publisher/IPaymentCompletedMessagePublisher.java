package com.food.ordering.system.payment.service.domain.ports.output.message.publisher;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;

/**
 * Publisher interface for publishing PaymentCompletedEvent domain events.
 */
public interface IPaymentCompletedMessagePublisher extends IDomainEventPublisher<PaymentCompletedEvent> {
}
