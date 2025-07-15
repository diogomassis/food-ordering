package com.food.ordering.system.order.service.dataaccess.restaurant.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.food.ordering.system.order.service.dataaccess.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.order.service.dataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.restaurant.repository.IRestaurantJpaRepository;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.ports.output.repository.IRestaurantRepository;

/**
 * Repository adapter implementation for restaurant data access operations.
 * Acts as a bridge between the domain layer and the data access layer,
 * implementing the IRestaurantRepository port interface.
 */
public class RestaurantRepository implements IRestaurantRepository {
    /**
     * JPA repository for restaurant entity database operations.
     */
    private final IRestaurantJpaRepository restaurantJpaRepository;

    /**
     * Mapper for converting between restaurant entities and domain objects.
     */
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    /**
     * Constructs a new RestaurantRepository with the required dependencies.
     * 
     * @param restaurantJpaRepository    the JPA repository for restaurant database
     *                                   operations
     * @param restaurantDataAccessMapper the mapper for entity-domain object
     *                                   conversion
     */
    public RestaurantRepository(IRestaurantJpaRepository restaurantJpaRepository,
            RestaurantDataAccessMapper restaurantDataAccessMapper) {
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.restaurantDataAccessMapper = restaurantDataAccessMapper;
    }

    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        List<UUID> restaurantProducts = restaurantDataAccessMapper.restaurantToRestaurantProducts(restaurant);
        Optional<List<RestaurantEntity>> restaurantEntities = restaurantJpaRepository
                .findByRestaurantIdAndProductIdIn(restaurant.getId().getValue(),
                        restaurantProducts);
        return restaurantEntities.map(restaurantDataAccessMapper::restaurantEntityToRestaurant);
    }
}
