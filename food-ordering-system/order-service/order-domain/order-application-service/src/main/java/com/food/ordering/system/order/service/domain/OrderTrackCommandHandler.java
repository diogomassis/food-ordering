package com.food.ordering.system.order.service.domain;

import org.springframework.stereotype.Component;

import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;

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

    /**
     * Tracks the status of an order based on the provided {@link TrackOrderQuery}.
     *
     * @param trackOrderQuery the query containing information required to track the
     *                        order
     * @return a {@link TrackOrderResponse} containing the tracking details of the
     *         order
     */
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return new TrackOrderResponse(null, null, null);
    }
}
