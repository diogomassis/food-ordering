package com.food.ordering.system.domain.exception;

/**
 * Exception thrown to indicate a domain-specific error within the food ordering
 * system.
 * <p>
 * This exception is typically used to signal business rule violations or other
 * domain-related issues that should be handled at the application level.
 * </p>
 */
public class DomainException extends RuntimeException {
    /**
     * Constructs a new DomainException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public DomainException(String message) {
        super(message);
    }

    /**
     * Constructs a new DomainException with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the
     *                {@link #getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).
     *                A null value is permitted, and indicates that the cause is
     *                nonexistent or unknown.
     */
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
