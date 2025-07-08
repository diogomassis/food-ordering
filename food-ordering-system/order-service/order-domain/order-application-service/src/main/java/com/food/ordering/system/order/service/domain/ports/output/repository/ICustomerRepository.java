package com.food.ordering.system.order.service.domain.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

import com.food.ordering.system.order.service.domain.entity.Customer;

/**
 * Repository interface for accessing customer data.
 * Provides method to find a customer by ID.
 */
public interface ICustomerRepository {
    /**
     * Finds a customer by their unique identifier.
     *
     * @param customerId the unique identifier of the customer
     * @return an {@link Optional} containing the found customer, or empty if not
     *         found
     */
    Optional<Customer> findCustomer(UUID customerId);
}
