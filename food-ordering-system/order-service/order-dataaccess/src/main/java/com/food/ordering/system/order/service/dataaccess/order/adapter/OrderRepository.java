package com.food.ordering.system.order.service.dataaccess.order.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.food.ordering.system.order.service.dataaccess.order.entity.OrderEntity;
import com.food.ordering.system.order.service.dataaccess.order.mapper.OrderDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.order.repository.IOrderJpaRepository;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.ports.output.repository.IOrderRepository;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

/**
 * Repository adapter implementation for Order entities.
 * This class serves as an adapter between the domain layer and the data access
 * layer,
 * implementing the IOrderRepository interface and providing concrete
 * implementations
 * for order persistence operations.
 */
@Component
public class OrderRepository implements IOrderRepository {

    /**
     * JPA repository for performing database operations on OrderEntity objects.
     */
    private final IOrderJpaRepository orderJpaRepository;

    /**
     * Mapper for converting between Order domain objects and OrderEntity data
     * access objects.
     */
    private final OrderDataAccessMapper orderDataAccessMapper;

    /**
     * Constructs a new OrderRepository with the required dependencies.
     * 
     * @param orderJpaRepository    the JPA repository for order database operations
     * @param orderDataAccessMapper the mapper for converting between domain and
     *                              entity objects
     */
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
