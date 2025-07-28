package com.food.ordering.system.order.service.domain.event;

import java.time.ZonedDateTime;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.order.service.domain.entity.Order;

/**
 * Domain event representing the successful payment of an order in the food
 * ordering system.
 * <p>
 * This event is published when an {@link Order} transitions to the PAID state,
 * indicating that payment has been processed and the order is ready for further
 * processing (such as approval or fulfillment). The event contains a reference
 * to the affected order and the timestamp when the payment was completed.
 * </p>
 *
 * <p>
 * This class extends {@link OrderEvent} and is typically used to trigger
 * subsequent domain actions or notifications related to the order payment
 * lifecycle.
 * </p>
 */
public class OrderPaidEvent extends OrderEvent {
    /**
     * Publisher responsible for publishing the order paid domain event.
     */
    private final IDomainEventPublisher<OrderPaidEvent> domainEventPublisher;

    /**
     * Constructs a new {@code OrderPaidEvent} for the specified order and
     * timestamp.
     *
     * @param order                the order that was paid
     * @param createdAt            the time when the payment event was created
     * @param domainEventPublisher the publisher for this event
     */
    public OrderPaidEvent(Order order, ZonedDateTime createdAt,
            IDomainEventPublisher<OrderPaidEvent> domainEventPublisher) {
        super(order, createdAt);
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void fire() {
        domainEventPublisher.publish(this);
    }
}
