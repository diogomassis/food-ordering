package com.food.ordering.system.order.service.domain.dto.track;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Data transfer object representing a query to track an order by its tracking
 * ID.
 */
@Getter
@Builder
@AllArgsConstructor
public class TrackOrderQuery {
    /**
     * The unique tracking identifier of the order to be tracked.
     */
    @NotNull
    private final UUID orderTrackingId;
}
