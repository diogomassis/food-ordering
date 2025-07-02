package com.food.ordering.system.order.service.domain.valueobject;

import java.util.UUID;

import com.food.ordering.system.domain.valueobject.BaseId;

/**
 * Value object representing a unique tracking identifier for an order.
 * <p>
 * This class extends {@link BaseId} with a {@link UUID} value to ensure
 * type safety and immutability for tracking IDs within the order domain.
 * </p>
 */
public class TrackingId extends BaseId<UUID> {
    /**
     * Constructs a new {@code TrackingId} with the specified UUID value.
     *
     * @param value the unique identifier for tracking an order
     */
    public TrackingId(UUID value) {
        super(value);
    }
}
