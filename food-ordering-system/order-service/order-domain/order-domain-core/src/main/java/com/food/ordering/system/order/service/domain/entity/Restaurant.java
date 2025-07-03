package com.food.ordering.system.order.service.domain.entity;

import java.util.List;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.RestaurantId;

/**
 * Represents a Restaurant aggregate root in the food ordering system domain.
 * <p>
 * A Restaurant contains information about its products and active status.
 * </p>
 *
 * <p>
 * Use the {@link Builder} to create instances of this class.
 * </p>
 */
public class Restaurant extends AggregateRoot<RestaurantId> {
    /**
     * The list of products offered by the restaurant.
     * Each {@link Product} represents a menu item available for ordering.
     */
    private final List<Product> products;
    /**
     * Indicates whether the restaurant is currently active and able to accept
     * orders.
     */
    private boolean active;

    /**
     * Constructs a Restaurant instance using the provided Builder.
     * <p>
     * This private constructor initializes the Restaurant entity with values from
     * the Builder,
     * including the restaurant identifier, products, and active status.
     *
     * @param builder the Builder instance containing all necessary Restaurant
     *                attributes
     */
    private Restaurant(Builder builder) {
        setId(builder.restaurantId);
        products = builder.products;
        active = builder.active;
    }

    /**
     * Retrieves the list of products offered by the restaurant.
     *
     * @return a list of {@link Product} objects representing the restaurant's menu
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Checks if the restaurant is currently active and able to accept orders.
     *
     * @return {@code true} if the restaurant is active, {@code false} otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Creates a new instance of the {@link Builder} for constructing
     * {@link Restaurant} objects.
     *
     * @return a new {@link Builder} instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing instances of {@link Restaurant}.
     * <p>
     * This builder provides a fluent API to set the various properties required to
     * create a {@link Restaurant} object,
     * such as the restaurant identifier, products, and active status.
     * </p>
     * <p>
     * Usage example:
     * 
     * <pre>
     *     Restaurant restaurant = Restaurant.builder()
     *         .restaurantId(new RestaurantId(...))
     *         .products(productList)
     *         .active(true)
     *         .build();
     * </pre>
     * </p>
     * <p>
     * All fields are optional until set, but required fields must be provided
     * before calling {@link #build()}.
     * </p>
     */
    public static final class Builder {
        /**
         * Unique identifier for the Restaurant entity.
         */
        private RestaurantId restaurantId;
        /**
         * The list of products offered by the restaurant.
         */
        private List<Product> products;
        /**
         * Indicates whether the restaurant is currently active.
         */
        private boolean active;

        /**
         * Private constructor for the {@code Builder} class.
         * Prevents direct instantiation from outside the {@code Builder},
         * enforcing the use of the enclosing class's builder pattern.
         */
        private Builder() {
        }

        /**
         * Sets the unique identifier for the restaurant.
         *
         * @param restaurantId the {@link RestaurantId} representing the unique ID of
         *                     the restaurant
         * @return the current {@code Builder} instance for method chaining
         */
        public Builder restaurantId(RestaurantId restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        /**
         * Sets the list of {@link Product} objects for the restaurant.
         *
         * @param products the list of products to be offered by the restaurant
         * @return the current Builder instance for method chaining
         */
        public Builder products(List<Product> products) {
            this.products = products;
            return this;
        }

        /**
         * Sets the active status for the restaurant.
         *
         * @param active {@code true} if the restaurant is active, {@code false}
         *               otherwise
         * @return the current Builder instance for method chaining
         */
        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        /**
         * Builds and returns a new {@link Restaurant} instance using the current state
         * of the builder.
         *
         * @return a new {@code Restaurant} object constructed from the builder's
         *         properties
         */
        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
