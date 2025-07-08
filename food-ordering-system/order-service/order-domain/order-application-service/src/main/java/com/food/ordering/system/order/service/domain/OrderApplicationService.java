package com.food.ordering.system.order.service.domain;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.ports.input.service.IOrderApplicationService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class responsible for handling order-related application logic.
 * <p>
 * Implements the {@link IOrderApplicationService} interface to provide
 * functionalities such as creating and tracking orders.
 * </p>
 * <p>
 * This class acts as a facade between the presentation layer and the domain
 * layer,
 * orchestrating order operations and delegating business logic to the
 * appropriate domain services.
 * </p>
 */
@Slf4j
@Validated
@Service
class OrderApplicationService implements IOrderApplicationService {
    /**
     * Handler responsible for processing order creation commands.
     */
    private final OrderCreateCommandHandler orderCreateCommandHandler;

    /**
     * Handler responsible for processing order tracking commands.
     */
    private final OrderTrackCommandHandler orderTrackCommandHandler;

    /**
     * Constructs a new OrderApplicationService with the specified command handlers.
     * 
     * @param orderCreateCommandHandler the handler responsible for processing order
     *                                  creation commands
     * @param orderTrackCommandHandler  the handler responsible for processing order
     *                                  tracking commands
     */
    public OrderApplicationService(OrderCreateCommandHandler orderCreateCommandHandler,
            OrderTrackCommandHandler orderTrackCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderTrackCommandHandler = orderTrackCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }
}
