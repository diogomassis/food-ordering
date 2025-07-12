package com.food.ordering.system.order.service.application.rest;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.ports.input.service.IOrderApplicationService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST controller for managing orders in the food ordering system.
 * Provides endpoints to create a new order and track an existing order by
 * tracking ID.
 */
@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = "application/vnd.api.v1+json")
public class OrderController {
    /**
     * Service for handling order application logic.
     */
    private final IOrderApplicationService orderApplicationService;

    /**
     * Constructs an OrderController with the specified order application service.
     *
     * @param orderApplicationService the service to handle order operations
     */
    public OrderController(IOrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    /**
     * Creates a new order based on the provided command.
     *
     * @param createOrderCommand the command containing order details
     * @return the response containing order creation details
     */
    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        log.info("Creating order for customer {} at restaurant {}", createOrderCommand.getCustomerId(),
                createOrderCommand.getRestaurantId());
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        log.info("Order created with tracking id {}", createOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(createOrderResponse);
    }

    /**
     * Retrieves the status of an order by its tracking ID.
     *
     * @param trackingId the unique tracking ID of the order
     * @return the response containing order tracking details
     */
    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable UUID trackingId) {
        TrackOrderQuery trackOrderQuery = TrackOrderQuery.builder().orderTrackingId(trackingId).build();
        TrackOrderResponse trackOrderResponse = orderApplicationService.trackOrder(trackOrderQuery);
        log.info("Returning order status with tracking id {}", trackOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(trackOrderResponse);
    }
}
