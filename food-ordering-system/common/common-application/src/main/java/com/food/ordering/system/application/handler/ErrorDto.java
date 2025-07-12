package com.food.ordering.system.application.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object representing an error response.
 * Contains an error code and a descriptive message.
 */
@Data
@Builder
@AllArgsConstructor
public class ErrorDto {
    /**
     * The unique code identifying the error.
     */
    private final String code;

    /**
     * The human-readable message describing the error.
     */
    private final String message;
}
