package com.food.ordering.system.order.service.domain;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;

import lombok.extern.slf4j.Slf4j;

/**
 * Application listener for {@link OrderCreatedEvent} domain events.
 * <p>
 * Listens for order creation events and publishes payment request messages
 * using the provided publisher.
 * </p>
 */
@Slf4j
@Component
public class OrderCreatedEventApplicationListener {
    /**
     * Publisher for sending payment request messages when an order is created.
     */
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    /**
     * Constructs an {@code OrderCreatedEventApplicationListener} with the specified
     * publisher.
     *
     * @param orderCreatedPaymentRequestMessagePublisher the publisher for payment
     *                                                   request messages
     */
    public OrderCreatedEventApplicationListener(
            OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
    }

    /**
     * Handles {@link OrderCreatedEvent} events and publishes payment requests.
     *
     * @param orderCreatedEvent the event representing the created order
     */
    @TransactionalEventListener
    void process(OrderCreatedEvent orderCreatedEvent) {
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
    }
}
