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
 * and returning the corresponding {@link TrackOrderResponse}. It interacts with
 * the order repository to retrieve order information based on a tracking ID and
 * maps the domain entity to a response DTO.
 * </p>
 */
@Slf4j
@Component
public class OrderTrackCommandHandler {
    /**
     * Mapper for converting between order domain entities and data transfer
     * objects.
     */
    private final OrderDataMapper orderDataMapper;

    /**
     * Repository for accessing order data from the persistence layer.
     */
    private final IOrderRepository orderRepository;

    /**
     * Constructs an {@code OrderTrackCommandHandler} with required dependencies.
     *
     * @param orderDataMapper the mapper for converting between domain entities and
     *                        DTOs
     * @param orderRepository the repository for retrieving order data
     */
    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, IOrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    /**
     * Tracks the status of an order based on the provided {@link TrackOrderQuery}.
     * <p>
     * Retrieves the order by its tracking ID, throws an exception if not found,
     * and maps the order entity to a response DTO.
     * </p>
     *
     * @param trackOrderQuery the query containing information required to track the
     *                        order
     * @return a {@link TrackOrderResponse} containing the tracking details of the
     *         order
     * @throws OrderNotFoundException if the order with the given tracking ID is not
     *                                found
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
