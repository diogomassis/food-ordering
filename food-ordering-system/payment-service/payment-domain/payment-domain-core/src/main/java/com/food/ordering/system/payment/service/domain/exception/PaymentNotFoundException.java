package com.food.ordering.system.payment.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

/**
 * Exception thrown when a requested payment entity cannot be found in the
 * payment service domain.
 * This exception indicates that a payment with a specific identifier does not
 * exist
 * in the system or is not accessible.
 */
public class PaymentNotFoundException extends DomainException {
    /**
     * Constructs a new PaymentNotFoundException with the specified detail message.
     *
     * @param message the detail message describing the payment that was not found
     */
    public PaymentNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new PaymentNotFoundException with the specified detail message
     * and cause.
     *
     * @param message the detail message describing the payment that was not found
     * @param cause   the cause of this exception (which is saved for later
     *                retrieval by the getCause() method)
     */
    public PaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
