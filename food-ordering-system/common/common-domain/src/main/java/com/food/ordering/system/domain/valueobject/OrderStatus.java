package com.food.ordering.system.domain.valueobject;

/**
 * Represents the various statuses an order can have in the food ordering
 * system.
 * <ul>
 * <li>{@code PENDING} - The order has been created but not yet paid.</li>
 * <li>{@code PAID} - The order has been paid by the customer.</li>
 * <li>{@code APPROVED} - The order has been approved for processing.</li>
 * <li>{@code CANCELLING} - The order is in the process of being cancelled.</li>
 * <li>{@code CANCELLED} - The order has been cancelled.</li>
 * </ul>
 */
public enum OrderStatus {
    PENDING, PAID, APPROVED, CANCELLING, CANCELLED
}
