package com.food.ordering.system.order.service.dataaccess.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.ordering.system.order.service.dataaccess.order.entity.OrderEntity;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

/**
 * JPA repository interface for managing {@link OrderEntity} persistence
 * operations.
 * Extends Spring Data JPA repository to provide standard CRUD operations
 * and custom query methods for order-related data access.
 */
@Repository
public interface IOrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    /**
     * Finds an order entity by its tracking identifier.
     * 
     * @param trackingId the unique tracking identifier of the order
     * @return an {@link Optional} containing the order entity if found,
     *         or empty if no order exists with the given tracking ID
     */
    Optional<OrderEntity> findByTrackingId(TrackingId trackingId);
}
