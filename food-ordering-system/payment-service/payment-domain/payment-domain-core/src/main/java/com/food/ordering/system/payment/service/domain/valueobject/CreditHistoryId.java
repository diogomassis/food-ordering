package com.food.ordering.system.payment.service.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

/**
 * Value object representing a unique identifier for credit history records in
 * the payment domain.
 * This class extends BaseId to provide type-safe UUID-based identification for
 * credit history entries.
 */
public class CreditHistoryId extends BaseId<UUID> {
    /**
     * Constructs a new CreditHistoryId with the specified UUID value.
     *
     * @param value the UUID value that uniquely identifies a credit history record
     */
    public CreditHistoryId(UUID value) {
        super(value);
    }
}
