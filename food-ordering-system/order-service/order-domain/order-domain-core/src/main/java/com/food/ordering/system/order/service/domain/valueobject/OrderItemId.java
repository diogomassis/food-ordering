package com.food.ordering.system.order.service.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

/**
 * Value object representing the unique identifier for an Order Item.
 * Extends {@link BaseId} with a {@link Long} value type.
 * Used to uniquely identify items within an order in the food ordering system.
 */
public class OrderItemId extends BaseId<Long> {
    /**
     * Constructs a new {@code OrderItemId} with the specified value.
     *
     * @param value the unique identifier for the order item
     */
    protected OrderItemId(Long value) {
        super(value);
    }
}
