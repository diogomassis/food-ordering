package com.food.ordering.system.order.service.domain;

import java.util.List;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

public class OrderDomainService implements IOrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateAndInitiateOrder'");
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'payOrder'");
    }

    @Override
    public void approveOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'approveOrder'");
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelOrderPayment'");
    }

    @Override
    public void cancelOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelOrder'");
    }
}
