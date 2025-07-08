package com.food.ordering.system.order.service.domain;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles the creation of orders by processing {@link CreateOrderCommand}
 * requests.
 * Validates customer and restaurant existence, maps DTOs to domain entities,
 * initiates order creation, and persists the order.
 */
@Slf4j
@Component
public class OrderCreateCommandHandler {
    /**
     * The mapper used to convert between domain entities and data transfer objects.
     */
    private final OrderDataMapper orderDataMapper;

    /**
     * Helper class that assists with the business logic for creating orders.
     */
    private final OrderCreateHelper orderCreateHelper;

    /**
     * Constructs an instance of {@code OrderCreateCommandHandler} with the
     * specified dependencies.
     *
     * @param orderDataMapper   the mapper used to convert between domain and data
     *                          transfer objects
     * @param orderCreateHelper the helper class to assist with order creation
     *                          logic
     */
    public OrderCreateCommandHandler(OrderDataMapper orderDataMapper, OrderCreateHelper orderCreateHelper) {
        this.orderDataMapper = orderDataMapper;
        this.orderCreateHelper = orderCreateHelper;
    }

    /**
     * Creates a new order based on the provided {@link CreateOrderCommand}.
     * Validates customer and restaurant, maps the command to an order entity,
     * initiates order creation, and persists the order.
     *
     * @param createOrderCommand the command containing order details
     * @return a {@link CreateOrderResponse} with order tracking information
     * @throws OrderDomainException if customer or restaurant is not found, or order
     *                              cannot be saved
     */
    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
    }
}
