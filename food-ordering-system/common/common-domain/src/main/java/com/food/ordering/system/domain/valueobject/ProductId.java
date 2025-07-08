package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * Value object representing the unique identifier for a Product.
 * <p>
 * This class extends {@link BaseId} with a {@link UUID} type to ensure
 * type safety and encapsulation of product identity within the domain.
 * </p>
 *
 * <p>
 * Instances of this class should be created using the protected constructor,
 * typically by factory methods or within the domain layer.
 * </p>
 */
public class ProductId extends BaseId<UUID> {
    /**
     * Constructs a new {@code ProductId} with the specified unique identifier.
     *
     * @param value the {@link UUID} value representing the unique identifier of the
     *              product
     */
    public ProductId(UUID value) {
        super(value);
    }
}
