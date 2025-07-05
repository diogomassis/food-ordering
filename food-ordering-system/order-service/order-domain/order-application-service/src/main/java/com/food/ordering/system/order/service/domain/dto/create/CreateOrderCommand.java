package com.food.ordering.system.order.service.domain.dto.create;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Command object for creating a new order.
 * <p>
 * Contains all necessary information to create an order, including customer and
 * restaurant IDs,
 * total price, list of order items, and delivery address.
 */
@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {
    /**
     * Unique identifier of the customer placing the order.
     */
    @NotNull
    private final UUID customerId;

    /**
     * Unique identifier of the restaurant from which the order is placed.
     */
    @NotNull
    private final UUID restaurantId;

    /**
     * Total price of the order.
     */
    @NotNull
    private final BigDecimal price;

    /**
     * List of items included in the order.
     */
    @NotNull
    private final List<OrderItem> items;

    /**
     * Delivery address for the order.
     */
    @NotNull
    private final OrderAddress address;
}
