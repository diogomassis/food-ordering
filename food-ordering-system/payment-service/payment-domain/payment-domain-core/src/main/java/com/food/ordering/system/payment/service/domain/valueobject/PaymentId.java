package com.food.ordering.system.payment.service.domain.valueobject;

import java.util.UUID;

import com.food.ordering.system.domain.valueobject.BaseId;

/**
 * Value object representing a unique identifier for payments in the payment
 * domain.
 * This class extends BaseId to provide type-safe UUID-based identification for
 * payment entities.
 */
public class PaymentId extends BaseId<UUID> {
    /**
     * Constructs a new PaymentId with the specified UUID value.
     *
     * @param value the UUID value that uniquely identifies a payment
     */
    public PaymentId(UUID value) {
        super(value);
    }
}
