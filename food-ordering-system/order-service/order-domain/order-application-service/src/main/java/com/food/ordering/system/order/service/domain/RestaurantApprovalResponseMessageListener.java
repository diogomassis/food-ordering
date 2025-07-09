package com.food.ordering.system.order.service.domain;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval.IRestaurantApprovalResponseMessageListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Listener implementation for handling restaurant approval response messages.
 * <p>
 * This service listens for order approval and rejection events from
 * restaurants,
 * and processes them accordingly. It implements the
 * {@link IRestaurantApprovalResponseMessageListener}
 * interface to handle restaurant approval-related callbacks.
 * </p>
 */
@Slf4j
@Validated
@Service
public class RestaurantApprovalResponseMessageListener implements IRestaurantApprovalResponseMessageListener {
    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orderApproved'");
    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orderRejected'");
    }
}
