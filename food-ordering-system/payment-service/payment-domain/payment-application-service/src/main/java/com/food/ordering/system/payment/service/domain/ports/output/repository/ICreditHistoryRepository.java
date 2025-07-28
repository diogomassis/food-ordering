package com.food.ordering.system.payment.service.domain.ports.output.repository;

import java.util.List;
import java.util.Optional;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;

/**
 * Repository interface for managing CreditHistory entities.
 */
public interface ICreditHistoryRepository {
    /**
     * Saves the given credit history entity.
     *
     * @param creditHistory The credit history entity to save
     * @return The saved credit history entity
     */
    CreditHistory save(CreditHistory creditHistory);

    /**
     * Finds credit histories by the associated customer ID.
     *
     * @param customerId The customer ID to search for
     * @return An Optional containing the list of CreditHistory, or empty if not
     *         found
     */
    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
