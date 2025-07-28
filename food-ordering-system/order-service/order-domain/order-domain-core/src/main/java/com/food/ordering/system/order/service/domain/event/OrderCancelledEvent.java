package com.food.ordering.system.order.service.domain.event;

import java.time.ZonedDateTime;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.order.service.domain.entity.Order;

/**
 * Domain event representing the cancellation of an order in the food ordering
 * system.
 * <p>
 * This event is published when an {@link Order} transitions to the CANCELLED
 * state, indicating that
 * the order has been cancelled due to a failure, user request, or business
 * rule. The event contains
 * a reference to the affected order and the timestamp when the cancellation
 * occurred.
 * </p>
 *
 * <p>
 * This class extends {@link OrderEvent} and is typically used to trigger
 * subsequent domain actions
 * or notifications related to the order cancellation lifecycle.
 * </p>
 */
public class OrderCancelledEvent extends OrderEvent {
    /**
     * Publisher responsible for publishing the order cancelled domain event.
     */
    private final IDomainEventPublisher<OrderCancelledEvent> domainEventPublisher;

    /**
     * Constructs a new {@code OrderCancelledEvent} for the specified order and
     * timestamp.
     *
     * @param order     the order that was cancelled
     * @param createdAt the time when the cancellation event was created
     * @param domainEventPublisher the publisher for this event
     */
    public OrderCancelledEvent(Order order, ZonedDateTime createdAt,
            IDomainEventPublisher<OrderCancelledEvent> domainEventPublisher) {
        super(order, createdAt);
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void fire() {
        domainEventPublisher.publish(this);
    }
}
