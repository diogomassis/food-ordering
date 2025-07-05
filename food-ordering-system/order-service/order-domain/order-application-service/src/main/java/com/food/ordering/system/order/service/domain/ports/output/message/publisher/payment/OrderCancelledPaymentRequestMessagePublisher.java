package com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;

/**
 * Publisher interface for sending payment request messages when an order is
 * cancelled.
 * Extends {@link IDomainEventPublisher} for {@link OrderCancelledEvent}.
 */
public interface OrderCancelledPaymentRequestMessagePublisher extends IDomainEventPublisher<OrderCancelledEvent> {

}
