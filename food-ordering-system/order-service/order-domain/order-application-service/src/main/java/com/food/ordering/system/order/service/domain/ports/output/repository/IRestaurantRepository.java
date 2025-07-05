package com.food.ordering.system.order.service.domain.ports.output.repository;

import java.util.Optional;

import com.food.ordering.system.order.service.domain.entity.Restaurant;

/**
 * Repository interface for accessing restaurant data.
 * Provides method to retrieve restaurant information.
 */
public interface IRestaurantRepository {
    /**
     * Finds restaurant information based on the provided restaurant entity.
     *
     * @param restaurant the restaurant entity with identifying information
     * @return an {@link Optional} containing the found restaurant, or empty if not
     *         found
     */
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
