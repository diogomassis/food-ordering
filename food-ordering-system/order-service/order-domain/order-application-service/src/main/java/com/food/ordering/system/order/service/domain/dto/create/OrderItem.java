package com.food.ordering.system.order.service.domain.dto.create;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Value object representing an item in an order.
 * <p>
 * Contains product ID, quantity, price per item, and subtotal for the item.
 */
@Getter
@Builder
@AllArgsConstructor
public class OrderItem {
    /**
     * Unique identifier of the product.
     */
    @NotNull
    private final UUID productId;

    /**
     * Quantity of the product ordered.
     */
    @NotNull
    private final Integer quantity;

    /**
     * Price per unit of the product.
     */
    @NotNull
    private final BigDecimal price;

    /**
     * Subtotal price for this item (quantity * price).
     */
    @NotNull
    private final BigDecimal subTotal;
}
