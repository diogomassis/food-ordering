package com.food.ordering.system.order.service.dataaccess.order.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.food.ordering.system.order.service.dataaccess.order.entity.OrderEntity;
import com.food.ordering.system.order.service.dataaccess.order.mapper.OrderDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.order.repository.IOrderJpaRepository;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.ports.output.repository.IOrderRepository;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

@Component
public class OrderRepository implements IOrderRepository {
    private final IOrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    public OrderRepository(IOrderJpaRepository orderJpaRepository, OrderDataAccessMapper orderDataAccessMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderDataAccessMapper.orderToOrderEntity(order);
        OrderEntity savedOrderEntity = orderJpaRepository.save(orderEntity);
        return orderDataAccessMapper.orderEntityToOrder(savedOrderEntity);
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        Optional<OrderEntity> foundOrder = orderJpaRepository.findByTrackingId(trackingId);
        return foundOrder.map(orderDataAccessMapper::orderEntityToOrder);
    }
}
