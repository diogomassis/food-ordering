package com.food.ordering.system.order.service.dataaccess.restaurant.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.dataaccess.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.order.service.dataaccess.restaurant.exception.RestaurantDataAccessException;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;

/**
 * Data access mapper for restaurant-related entities and domain objects.
 * Provides mapping functionality between restaurant entities and domain models
 * in the order service data access layer.
 */
@Component
public class RestaurantDataAccessMapper {

    /**
     * Converts a Restaurant domain object to a list of product UUIDs.
     * Extracts all product IDs from the restaurant's product list for database
     * queries.
     * 
     * @param restaurant the Restaurant domain object containing products
     * @return a list of UUID values representing the product IDs
     */
    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of RestaurantEntity objects to a Restaurant domain object.
     * Maps database entities to domain model, combining product information from
     * multiple entities
     * that share the same restaurant ID.
     * 
     * @param restaurantEntities the list of RestaurantEntity objects from the
     *                           database
     * @return a Restaurant domain object with mapped products and restaurant
     *         information
     * @throws RestaurantDataAccessException if no restaurant entities are found in
     *                                       the list
     */
    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity = restaurantEntities.stream().findFirst()
                .orElseThrow(() -> new RestaurantDataAccessException("Restaurant could not be found!"));

        List<Product> restaurantProducts = restaurantEntities.stream()
                .map(entity -> new Product(new ProductId(entity.getProductId()), entity.getProductName(),
                        new Money(entity.getProductPrice())))
                .toList();

        return Restaurant.builder()
                .restaurantId(new RestaurantId(restaurantEntity.getRestaurantId()))
                .products(restaurantProducts)
                .active(restaurantEntity.getRestaurantActive())
                .build();
    }
}
