package com.food.ordering.system.payment.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

/**
 * Custom domain exception for the payment service domain.
 * This exception is thrown when domain-specific business rule violations
 * or validation errors occur within the payment service domain layer.
 */
public class PaymentDomainException extends DomainException {
    /**
     * Constructs a new PaymentDomainException with the specified detail message.
     *
     * @param message the detail message describing the exception
     */
    public PaymentDomainException(String message) {
        super(message);
    }

    /**
     * Constructs a new PaymentDomainException with the specified detail message and
     * cause.
     *
     * @param message the detail message describing the exception
     * @param cause   the cause of this exception (which is saved for later
     *                retrieval by the getCause() method)
     */
    public PaymentDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
