package com.food.ordering.system.order.service.dataaccess.restaurant.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Composite primary key class for RestaurantEntity.
 * Implements Serializable to support JPA composite key requirements.
 * Contains restaurant ID and product ID that together form the unique
 * identifier.
 */
public class RestaurantEntityId implements Serializable {

    /**
     * Unique identifier for the restaurant.
     * Part of the composite primary key.
     */
    private UUID restaurantId;

    /**
     * Unique identifier for the product.
     * Part of the composite primary key.
     */
    private UUID productId;

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, productId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        RestaurantEntityId that = (RestaurantEntityId) obj;
        return restaurantId.equals(that.restaurantId) && productId.equals(that.productId);
    }
}
