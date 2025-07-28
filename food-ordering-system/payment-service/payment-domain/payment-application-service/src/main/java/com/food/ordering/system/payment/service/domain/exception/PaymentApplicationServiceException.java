package com.food.ordering.system.payment.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

/**
 * Exception thrown for errors occurring in the payment application service
 * layer.
 */
public class PaymentApplicationServiceException extends DomainException {
    /**
     * Constructs a new PaymentApplicationServiceException with the specified detail
     * message.
     *
     * @param message The detail message
     */
    public PaymentApplicationServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new PaymentApplicationServiceException with the specified detail
     * message and cause.
     *
     * @param message The detail message
     * @param cause   The cause of the exception
     */
    public PaymentApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
