package com.food.ordering.system.order.service.domain;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.repository.IOrderRepository;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles commands related to tracking orders in the order service domain.
 * <p>
 * This component is responsible for processing {@link TrackOrderQuery} requests
 * and returning the corresponding {@link TrackOrderResponse}.
 * </p>
 */
@Slf4j
@Component
public class OrderTrackCommandHandler {
    private final OrderDataMapper orderDataMapper;
    private final IOrderRepository orderRepository;

    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, IOrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    /**
     * Tracks the status of an order based on the provided {@link TrackOrderQuery}.
     *
     * @param trackOrderQuery the query containing information required to track the
     *                        order
     * @return a {@link TrackOrderResponse} containing the tracking details of the
     *         order
     */
    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> optionalOrder = orderRepository
                .findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if (optionalOrder.isEmpty()) {
            log.warn("Could not find order with tracking id {}", trackOrderQuery.getOrderTrackingId().toString());
            throw new OrderNotFoundException(
                    "Could not find order with tracking id " + trackOrderQuery.getOrderTrackingId().toString());
        }
        return orderDataMapper.orderToTrackOrderResponse(optionalOrder.get());
    }
}
