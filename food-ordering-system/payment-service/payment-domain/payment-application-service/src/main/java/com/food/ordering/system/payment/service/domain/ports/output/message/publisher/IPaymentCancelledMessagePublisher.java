package com.food.ordering.system.payment.service.domain.ports.output.message.publisher;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;

public interface IPaymentCancelledMessagePublisher extends IDomainEventPublisher<PaymentCancelledEvent> {
}
