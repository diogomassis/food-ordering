package com.food.ordering.system.order.service.dataaccess.restaurant.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA entity representing a restaurant with associated product information.
 * Maps to the order_restaurant_m_view materialized view in the restaurant
 * schema.
 * Uses composite primary key consisting of restaurantId and productId.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(RestaurantEntityId.class)
@Table(name = "order_restaurant_m_view", schema = "restaurant")
@Entity
public class RestaurantEntity {
    /**
     * Unique identifier for the restaurant.
     * Part of the composite primary key.
     */
    @Id
    private UUID restaurantId;

    /**
     * Unique identifier for the product.
     * Part of the composite primary key.
     */
    @Id
    private UUID productId;

    /**
     * Name of the restaurant.
     */
    private String restaurantName;

    /**
     * Indicates whether the restaurant is currently active and accepting orders.
     */
    private Boolean restaurantActive;

    /**
     * Name of the product offered by the restaurant.
     */
    private String productName;

    /**
     * Price of the product in the restaurant.
     */
    private BigDecimal productPrice;

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
        RestaurantEntity that = (RestaurantEntity) obj;
        return restaurantId.equals(that.restaurantId) && productId.equals(that.productId);
    }
}
