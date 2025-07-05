package com.food.ordering.system.domain.valueobject;

/**
 * Represents the payment status of an order in the food ordering system.
 * <ul>
 * <li>{@code COMPLETED} - The payment has been successfully completed.</li>
 * <li>{@code CANCELLED} - The payment has been cancelled.</li>
 * <li>{@code FAILED} - The payment attempt has failed.</li>
 * </ul>
 */
public enum PaymentStatus {
    COMPLETED,
    CANCELLED,
    FAILED
}
