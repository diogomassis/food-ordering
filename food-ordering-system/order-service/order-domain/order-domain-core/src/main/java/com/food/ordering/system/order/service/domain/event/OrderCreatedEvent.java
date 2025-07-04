package com.food.ordering.system.order.service.domain.event;

import java.time.ZonedDateTime;

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
     * Constructs a new {@code OrderCreatedEvent} for the specified order and
     * timestamp.
     *
     * @param order     the order that was created
     * @param createdAt the time when the creation event was created
     */
    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
