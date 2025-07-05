package com.food.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

/**
 * Publisher interface for sending restaurant approval request messages when an
 * order is paid.
 * Extends {@link IDomainEventPublisher} for {@link OrderPaidEvent}.
 */
public interface OrderPaidRestaurantRequestMessagePublisher extends IDomainEventPublisher<OrderPaidEvent> {

}
