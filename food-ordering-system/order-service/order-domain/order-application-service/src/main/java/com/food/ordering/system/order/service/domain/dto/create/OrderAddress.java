package com.food.ordering.system.order.service.domain.dto.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Value object representing the delivery address for an order.
 * <p>
 * Contains street, postal code, and city information.
 */
@Getter
@Builder
@AllArgsConstructor
public class OrderAddress {
    /**
     * Street name of the delivery address (max 50 characters).
     */
    @NotNull
    @Max(value = 50)
    private final String street;

    /**
     * Postal code of the delivery address (max 10 characters).
     */
    @NotNull
    @Max(value = 10)
    private final String postalCode;

    /**
     * City of the delivery address (max 50 characters).
     */
    @NotNull
    @Max(value = 50)
    private final String city;
}
