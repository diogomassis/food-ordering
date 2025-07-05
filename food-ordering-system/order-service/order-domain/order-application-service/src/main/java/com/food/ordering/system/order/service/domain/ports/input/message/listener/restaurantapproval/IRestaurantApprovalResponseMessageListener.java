package com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval;

import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;

/**
 * Listener interface for handling restaurant approval response messages.
 * Provides methods to process approved and rejected order events from
 * restaurants.
 */
public interface IRestaurantApprovalResponseMessageListener {
    /**
     * Handles the event when an order is approved by the restaurant.
     *
     * @param restaurantApprovalResponse the restaurant approval response data
     */
    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    /**
     * Handles the event when an order is rejected by the restaurant.
     *
     * @param restaurantApprovalResponse the restaurant approval response data
     */
    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
