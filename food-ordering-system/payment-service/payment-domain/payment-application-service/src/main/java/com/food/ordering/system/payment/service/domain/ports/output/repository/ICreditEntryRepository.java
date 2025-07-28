package com.food.ordering.system.payment.service.domain.ports.output.repository;

import java.util.Optional;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.payment.service.domain.entity.CreditEntry;

/**
 * Repository interface for managing CreditEntry entities.
 */
public interface ICreditEntryRepository {
    /**
     * Saves the given credit entry entity.
     *
     * @param creditEntry The credit entry entity to save
     * @return The saved credit entry entity
     */
    CreditEntry save(CreditEntry creditEntry);

    /**
     * Finds a credit entry by the associated customer ID.
     *
     * @param customerId The customer ID to search for
     * @return An Optional containing the found CreditEntry, or empty if not found
     */
    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
