package com.food.ordering.system.order.service.messaging.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.order.avro.model.PaymentOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;

/**
 * Data mapper component for converting order domain events to messaging models.
 * This mapper is responsible for transforming order events into Avro models
 * that can be published to Kafka topics for payment processing.
 */
@Component
public class OrderMessagingDataMapper {
    /**
     * Converts an OrderCreatedEvent to a PaymentRequestAvroModel for payment
     * processing.
     * This method maps the order creation event data to the payment request message
     * format
     * with a PENDING payment status.
     *
     * @param orderCreatedEvent the order created event containing order details
     * @return PaymentRequestAvroModel with payment request data in PENDING status
     */
    public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(OrderCreatedEvent orderCreatedEvent) {
        Order order = orderCreatedEvent.getOrder();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setSagaId(UUID.randomUUID())
                .setCustomerId(order.getCustomerId().getValue())
                .setOrderId(order.getId().getValue())
                .setPrice(order.getPrice().getAmount())
                .setCreatedAt(orderCreatedEvent.getCreatedAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
                .build();
    }

    /**
     * Converts an OrderCancelledEvent to a PaymentRequestAvroModel for payment
     * cancellation.
     * This method maps the order cancellation event data to the payment request
     * message format
     * with a CANCELLED payment status.
     *
     * @param orderCancelledEvent the order cancelled event containing order details
     * @return PaymentRequestAvroModel with payment request data in CANCELLED status
     */
    public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(
            OrderCancelledEvent orderCancelledEvent) {
        Order order = orderCancelledEvent.getOrder();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setSagaId(UUID.randomUUID())
                .setCustomerId(order.getCustomerId().getValue())
                .setOrderId(order.getId().getValue())
                .setPrice(order.getPrice().getAmount())
                .setCreatedAt(orderCancelledEvent.getCreatedAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
                .build();
    }
}
