package com.food.ordering.system.order.service.domain.ports.output.repository;

import java.util.Optional;

import com.food.ordering.system.order.service.domain.entity.Restaurant;

public interface IRestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
