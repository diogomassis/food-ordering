package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.ports.input.service.IOrderApplicationService;

import jakarta.validation.Valid;

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
public class OrderApplicationService implements IOrderApplicationService {
    @Override
    public CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }

    @Override
    public TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trackOrder'");
    }
}
