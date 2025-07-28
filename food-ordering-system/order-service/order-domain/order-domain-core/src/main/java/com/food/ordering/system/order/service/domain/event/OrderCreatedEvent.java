package com.food.ordering.system.order.service.domain.event;

import java.time.ZonedDateTime;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.order.service.domain.entity.Order;

/**
 * Domain event representing the creation of an order in the food ordering
 * system.
 * <p>
 * This event is published when an {@link Order} is successfully created and
 * initialized in the system.
 * It contains a reference to the affected order and the timestamp when the
 * creation occurred.
 * </p>
 *
 * <p>
 * This class extends {@link OrderEvent} and is typically used to trigger
 * subsequent domain actions
 * or notifications related to the order creation lifecycle.
 * </p>
 */
public class OrderCreatedEvent extends OrderEvent {
    /**
     * Publisher responsible for publishing the order created domain event.
     */
    private final IDomainEventPublisher<OrderCreatedEvent> domainEventPublisher;

    /**
     * Constructs a new {@code OrderCreatedEvent} for the specified order and
     * timestamp.
     *
     * @param order                the order that was created
     * @param createdAt            the time when the creation event was created
     * @param domainEventPublisher the publisher for this event
     */
    public OrderCreatedEvent(Order order, ZonedDateTime createdAt,
            IDomainEventPublisher<OrderCreatedEvent> domainEventPublisher) {
        super(order, createdAt);
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void fire() {
        domainEventPublisher.publish(this);
    }
}
