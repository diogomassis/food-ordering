package com.food.ordering.system.order.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

/**
 * Exception thrown to indicate a domain-specific error within the order
 * service.
 * <p>
 * This exception is used to signal issues that occur during the processing of
 * orders
 * in the domain layer, encapsulating domain-related business logic violations
 * or errors.
 * </p>
 *
 * @see DomainException
 */
public class OrderDomainException extends DomainException {
    /**
     * Constructs a new OrderDomainException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public OrderDomainException(String message) {
        super(message);
    }

    /**
     * Constructs a new OrderDomainException with the specified detail message and
     * cause.
     *
     * @param message the detail message (which is saved for later retrieval by the
     *                {@link Throwable#getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link Throwable#getCause()} method).
     *                A null value is permitted, and indicates that the cause is
     *                nonexistent or unknown.
     */
    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
