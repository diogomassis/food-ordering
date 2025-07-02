package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * Value object representing the unique identifier for an Order.
 * <p>
 * This class extends {@link BaseId} with a {@link UUID} type to ensure
 * type safety and encapsulation of order identifiers within the domain.
 * </p>
 */
public class OrderId extends BaseId<UUID> {

    /**
     * Constructs a new {@code OrderId} with the specified unique identifier.
     *
     * @param value the {@link UUID} representing the unique identifier for the
     *              order
     */
    protected OrderId(UUID value) {
        super(value);
    }
}
