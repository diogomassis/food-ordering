package com.food.ordering.system.application.handler;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler for the application.
 * Handles general and validation exceptions, returning standardized error
 * responses.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles all uncaught exceptions and returns a generic error response.
     *
     * @param exception the thrown exception
     * @return an {@link ErrorDto} with a generic error message
     */
    @ResponseBody
    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception exception) {
        return ErrorDto.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Unexpected error!").build();
    }

    /**
     * Handles validation exceptions and returns a detailed error response.
     * If the exception is a {@link ConstraintViolationException}, extracts all
     * violation messages.
     *
     * @param validationException the thrown validation exception
     * @return an {@link ErrorDto} with validation error details
     */
    @ResponseBody
    @ExceptionHandler(value = { ValidationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(ValidationException validationException) {
        ErrorDto errorDto;
        if (validationException instanceof ConstraintViolationException) {
            String violations = extractViolationsFromException((ConstraintViolationException) validationException);
            log.error(violations, validationException);
            errorDto = ErrorDto.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(violations).build();
        } else {
            String exceptionMessage = validationException.getMessage();
            log.error(exceptionMessage, validationException);
            errorDto = ErrorDto.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase()).message(exceptionMessage)
                    .build();
        }
        return errorDto;
    }

    /**
     * Extracts violation messages from a {@link ConstraintViolationException}.
     *
     * @param validationException the exception containing constraint violations
     * @return a string with all violation messages joined by "--"
     */
    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }
}
