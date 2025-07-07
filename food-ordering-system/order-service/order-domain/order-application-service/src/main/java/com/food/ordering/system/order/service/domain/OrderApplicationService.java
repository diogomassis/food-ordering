package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.ports.input.service.IOrderApplicationService;

import jakarta.validation.Valid;

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
