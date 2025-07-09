package com.food.ordering.system.order.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

/**
 * Exception thrown when an order cannot be found in the system.
 * <p>
 * This exception is used to indicate that a requested order does not exist or
 * could not be retrieved.
 * </p>
 */
public class OrderNotFoundException extends DomainException {
    /**
     * Constructs a new {@code OrderNotFoundException} with the specified detail
     * message.
     *
     * @param message the detail message
     */
    public OrderNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code OrderNotFoundException} with the specified detail
     * message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
