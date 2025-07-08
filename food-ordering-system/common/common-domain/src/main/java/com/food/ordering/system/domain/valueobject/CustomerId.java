package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * Value object representing a unique identifier for a Customer.
 * <p>
 * This class extends {@link BaseId} with a {@link UUID} type to ensure
 * type safety and encapsulation of customer identity within the domain.
 * </p>
 *
 * @author Your Name
 */
public class CustomerId extends BaseId<UUID> {
    /**
     * Constructs a new {@code CustomerId} with the specified unique identifier.
     *
     * @param value the {@link UUID} value representing the unique identifier for
     *              the customer
     */
    public CustomerId(UUID value) {
        super(value);
    }
}
