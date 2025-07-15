package com.food.ordering.system.order.service.dataaccess.restaurant.exception;

/**
 * Custom exception class for restaurant data access layer operations.
 * Thrown when errors occur during restaurant-related database operations
 * or data access operations in the order service.
 */
public class RestaurantDataAccessException extends RuntimeException {
    /**
     * Constructs a new RestaurantDataAccessException with the specified detail
     * message.
     * 
     * @param message the detail message explaining the cause of the exception
     */
    public RestaurantDataAccessException(String message) {
        super(message);
    }

    /**
     * Constructs a new RestaurantDataAccessException with the specified detail
     * message
     * and cause.
     * 
     * @param message the detail message explaining the cause of the exception
     * @param cause   the cause of the exception (which is saved for later retrieval
     *                by the getCause() method)
     */
    public RestaurantDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
