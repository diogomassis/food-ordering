package com.food.ordering.system.order.service.domain.dto.create;

import java.util.UUID;

import com.food.ordering.system.domain.valueobject.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Response object for the result of creating a new order.
 * <p>
 * Contains the order tracking ID, order status, and a message describing the
 * result.
 */
@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
    /**
     * Unique tracking identifier for the created order.
     */
    @NotNull
    private final UUID orderTrackingId;

    /**
     * Status of the created order.
     */
    @NotNull
    private final OrderStatus orderStatus;

    /**
     * Message describing the result of the order creation.
     */
    @NotNull
    private final String message;
}
