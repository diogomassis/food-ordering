package com.food.ordering.system.domain.valueobject;

/**
 * Represents the approval status of an order in the food ordering system.
 * <ul>
 * <li>{@code APPROVED} - The order has been approved by the restaurant.</li>
 * <li>{@code REJECTED} - The order has been rejected by the restaurant.</li>
 * </ul>
 */
public enum OrderApprovalStatus {
    APPROVED, REJECTED
}
