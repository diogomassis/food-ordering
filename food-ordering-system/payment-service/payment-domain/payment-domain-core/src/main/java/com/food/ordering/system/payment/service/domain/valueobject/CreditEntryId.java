package com.food.ordering.system.payment.service.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

/**
 * Value object representing a unique identifier for credit entries in the
 * payment domain.
 * This class extends BaseId to provide type-safe UUID-based identification for
 * credit entries.
 */
public class CreditEntryId extends BaseId<UUID> {
    /**
     * Constructs a new CreditEntryId with the specified UUID value.
     *
     * @param value the UUID value that uniquely identifies a credit entry
     */
    public CreditEntryId(UUID value) {
        super(value);
    }
}
