package com.food.ordering.system.order.service.domain.ports.input.service;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;

import jakarta.validation.Valid;

/**
 * Application service interface for handling order-related operations.
 * Provides methods to create and track orders.
 */
public interface IOrderApplicationService {
    /**
     * Creates a new order based on the provided command.
     *
     * @param createOrderCommand the command containing order creation details
     * @return the response containing order creation result
     */
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    /**
     * Tracks an order based on the provided query.
     *
     * @param trackOrderQuery the query containing tracking information
     * @return the response containing order tracking result
     */
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
