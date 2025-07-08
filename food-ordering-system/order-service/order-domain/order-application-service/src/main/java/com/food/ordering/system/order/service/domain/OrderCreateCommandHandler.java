package com.food.ordering.system.order.service.domain;

import org.springframework.stereotype.Component;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderCreateCommandHandler {
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return new CreateOrderResponse(null, null, null);
    }
}
