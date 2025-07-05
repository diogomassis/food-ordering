package com.food.ordering.system.order.service.domain.dto.message;

import java.time.Instant;
import java.util.List;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Data transfer object representing the response of a restaurant's approval
 * operation for an order.
 */
@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalResponse {
    /**
     * Unique identifier for the restaurant approval response.
     */
    private String id;

    /**
     * Identifier for the saga process associated with this approval.
     */
    private String sagaId;

    /**
     * Identifier of the order related to the approval.
     */
    private String orderId;

    /**
     * Identifier of the restaurant processing the approval.
     */
    private String restaurantId;

    /**
     * The timestamp when the approval response was created.
     */
    private Instant createdAt;

    /**
     * The approval status of the order.
     */
    private OrderApprovalStatus orderApprovalStatus;

    /**
     * List of failure messages if the approval failed.
     */
    private List<String> failureMessages;
}
