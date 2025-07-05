package com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;

/**
 * Publisher interface for sending payment request messages when an order is
 * created.
 * Extends {@link IDomainEventPublisher} for {@link OrderCreatedEvent}.
 */
public interface OrderCreatedPaymentRequestMessagePublisher extends IDomainEventPublisher<OrderCreatedEvent> {

}
