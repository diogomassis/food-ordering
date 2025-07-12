package com.food.ordering.system.order.service.application.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.food.ordering.system.application.handler.ErrorDto;
import com.food.ordering.system.application.handler.GlobalExceptionHandler;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler for the Order service.
 * Handles order-specific exceptions and returns standardized error responses.
 */
@Slf4j
@ControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {
    /**
     * Handles {@link OrderDomainException} and returns a BAD_REQUEST error
     * response.
     *
     * @param orderDomainException the thrown domain exception
     * @return an {@link ErrorDto} with error details
     */
    @ResponseBody
    @ExceptionHandler(value = { OrderDomainException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(OrderDomainException orderDomainException) {
        log.error(orderDomainException.getMessage(), orderDomainException);
        return ErrorDto.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(orderDomainException.getMessage()).build();
    }

    /**
     * Handles {@link OrderNotFoundException} and returns a NOT_FOUND error
     * response.
     *
     * @param orderNotFoundException the thrown not found exception
     * @return an {@link ErrorDto} with error details
     */
    @ResponseBody
    @ExceptionHandler(value = { OrderNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleException(OrderNotFoundException orderNotFoundException) {
        log.error(orderNotFoundException.getMessage(), orderNotFoundException);
        return ErrorDto.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(orderNotFoundException.getMessage()).build();
    }
}
