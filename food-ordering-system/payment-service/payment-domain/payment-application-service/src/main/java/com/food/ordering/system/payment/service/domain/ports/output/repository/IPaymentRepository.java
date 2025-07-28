package com.food.ordering.system.payment.service.domain.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

import com.food.ordering.system.payment.service.domain.entity.Payment;

/**
 * Repository interface for managing Payment entities.
 */
public interface IPaymentRepository {
    /**
     * Saves the given payment entity.
     *
     * @param payment The payment entity to save
     * @return The saved payment entity
     */
    Payment save(Payment payment);

    /**
     * Finds a payment by its associated order ID.
     *
     * @param orderId The order ID to search for
     * @return An Optional containing the found Payment, or empty if not found
     */
    Optional<Payment> findByOrderId(UUID orderId);
}
