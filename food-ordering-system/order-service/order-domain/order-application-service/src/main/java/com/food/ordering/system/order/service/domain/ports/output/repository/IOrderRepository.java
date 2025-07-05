package com.food.ordering.system.order.service.domain.ports.output.repository;

import java.util.Optional;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

/**
 * Repository interface for accessing order data.
 * Provides methods to save and retrieve orders.
 */
public interface IOrderRepository {
    /**
     * Saves the given order entity.
     *
     * @param order the order entity to save
     * @return the saved order entity
     */
    Order save(Order order);

    /**
     * Finds an order by its tracking ID.
     *
     * @param trackingId the tracking ID of the order
     * @return an {@link Optional} containing the found order, or empty if not found
     */
    Optional<Order> findByTrackingId(TrackingId trackingId);
}
