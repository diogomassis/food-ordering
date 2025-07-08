package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * Value object representing the unique identifier for a Restaurant.
 * <p>
 * This class extends {@link BaseId} with a {@link UUID} type to ensure
 * type safety and encapsulation of restaurant identity within the domain.
 * </p>
 */
public class RestaurantId extends BaseId<UUID> {
    /**
     * Constructs a new {@code RestaurantId} with the specified unique identifier.
     *
     * @param value the {@link UUID} representing the unique identifier of the
     *              restaurant
     */
    public RestaurantId(UUID value) {
        super(value);
    }
}
