package com.food.ordering.system.order.service.domain.event;

import java.time.ZonedDateTime;

import com.food.ordering.system.domain.event.IDomainEvent;
import com.food.ordering.system.order.service.domain.entity.Order;

/**
 * Abstract base class for domain events related to orders in the food ordering
 * system.
 * <p>
 * This class implements the {@link IDomainEvent} interface for the
 * {@link Order} aggregate and provides
 * common properties and behavior for all order-related events, such as the
 * associated order and the event
 * creation timestamp. Concrete subclasses represent specific order events
 * (e.g., paid, cancelled).
 * </p>
 */
public abstract class OrderEvent implements IDomainEvent<Order> {
    /**
     * The order associated with this domain event.
     */
    private final Order order;
    /**
     * The timestamp when the event was created.
     */
    private final ZonedDateTime createdAt;

    /**
     * Constructs a new {@code OrderEvent} for the specified order and timestamp.
     *
     * @param order     the order associated with the event
     * @param createdAt the time when the event was created
     */
    public OrderEvent(Order order, ZonedDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

    /**
     * Returns the order associated with this event.
     *
     * @return the {@link Order} instance
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Returns the timestamp when the event was created.
     *
     * @return the event creation time as {@link ZonedDateTime}
     */
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
