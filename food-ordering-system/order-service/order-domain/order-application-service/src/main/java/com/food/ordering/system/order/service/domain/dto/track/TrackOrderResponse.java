package com.food.ordering.system.order.service.domain.dto.track;

import java.util.List;
import java.util.UUID;

import com.food.ordering.system.domain.valueobject.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Data transfer object representing the response to a track order query.
 */
@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {
    /**
     * The unique tracking identifier of the order.
     */
    @NotNull
    private final UUID orderTrackingId;

    /**
     * The current status of the order.
     */
    @NotNull
    private final OrderStatus orderStatus;

    /**
     * List of failure messages if there were issues tracking the order.
     */
    private final List<String> failureMessages;
}
