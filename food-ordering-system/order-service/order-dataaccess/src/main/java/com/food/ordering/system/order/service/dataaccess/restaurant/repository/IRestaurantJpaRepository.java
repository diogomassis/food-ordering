package com.food.ordering.system.order.service.dataaccess.restaurant.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.ordering.system.order.service.dataaccess.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.order.service.dataaccess.restaurant.entity.RestaurantEntityId;

/**
 * JPA repository interface for managing Restaurant entities in the order
 * service data access layer.
 * Provides data access operations for restaurant-related queries and
 * operations.
 * 
 * @author Food Ordering System
 * @since 1.0
 */
@Repository
public interface IRestaurantJpaRepository extends JpaRepository<RestaurantEntity, RestaurantEntityId> {
    /**
     * Finds restaurant entities by restaurant ID and a list of product IDs.
     * This method is used to retrieve restaurants that have specific products
     * available.
     * 
     * @param restaurantId the UUID of the restaurant to search for
     * @param productIds   the list of product UUIDs to match against
     * @return an Optional containing a list of RestaurantEntity objects that match
     *         the criteria,
     *         or empty Optional if no matches are found
     */
    Optional<List<RestaurantEntity>> findByRestaurantIdAndProductIdIn(UUID restaurantId, List<UUID> productIds);
}
