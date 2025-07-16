package com.food.ordering.system.order.service.messaging.mapper;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.order.avro.model.PaymentOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus;
import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

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

        /**
         * Converts an OrderPaidEvent to a RestaurantApprovalRequestAvroModel for
         * restaurant approval processing.
         * This method maps the order paid event data to the restaurant approval request
         * message format
         * with a PAID restaurant order status, including product details and pricing
         * information.
         *
         * @param orderPaidEvent the order paid event containing order details after
         *                       successful payment
         * @return RestaurantApprovalRequestAvroModel with restaurant approval request
         *         data in PAID status
         */
        public RestaurantApprovalRequestAvroModel orderPaidEventToRestaurantApprovalRequestAvroModel(
                        OrderPaidEvent orderPaidEvent) {
                Order order = orderPaidEvent.getOrder();
                return RestaurantApprovalRequestAvroModel.newBuilder()
                                .setId(UUID.randomUUID())
                                .setSagaId(UUID.randomUUID())
                                .setOrderId(order.getId().getValue())
                                .setRestaurantId(order.getRestaurantId().getValue())
                                .setOrderId(order.getId().getValue())
                                .setRestaurantOrderStatus(
                                                com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus
                                                                .valueOf(order.getOrderStatus().name()))
                                .setProducts(order.getItems().stream()
                                                .map(orderItem -> com.food.ordering.system.kafka.order.avro.model.Product
                                                                .newBuilder()
                                                                .setId(orderItem.getProduct().getId().getValue()
                                                                                .toString())
                                                                .setQuantity(orderItem.getQuantity())
                                                                .build())
                                                .collect(Collectors.toList()))
                                .setPrice(order.getPrice().getAmount())
                                .setCreatedAt(orderPaidEvent.getCreatedAt().toInstant())
                                .setRestaurantOrderStatus(RestaurantOrderStatus.PAID)
                                .build();
        }

        public PaymentResponse paymentResponseAvroModelToPaymentResponse(
                        PaymentResponseAvroModel paymentResponseAvroModel) {
                return PaymentResponse.builder()
                                .id(paymentResponseAvroModel.getId().toString())
                                .sagaId(paymentResponseAvroModel.getSagaId().toString())
                                .paymentId(paymentResponseAvroModel.getPaymentId().toString())
                                .customerId(paymentResponseAvroModel.getCustomerId().toString())
                                .orderId(paymentResponseAvroModel.getOrderId().toString())
                                .price(paymentResponseAvroModel.getPrice())
                                .createdAt(paymentResponseAvroModel.getCreatedAt())
                                .paymentStatus(com.food.ordering.system.domain.valueobject.PaymentStatus.valueOf(
                                                paymentResponseAvroModel.getPaymentStatus().name()))
                                .failureMessages(paymentResponseAvroModel.getFailureMessages())
                                .build();
        }

        public RestaurantApprovalResponse approvalResponseAvroModelToApprovalResponse(
                        RestaurantApprovalResponseAvroModel restaurantApprovalResponseAvroModel) {
                return RestaurantApprovalResponse.builder()
                                .id(restaurantApprovalResponseAvroModel.getId().toString())
                                .sagaId(restaurantApprovalResponseAvroModel.getSagaId().toString())
                                .restaurantId(restaurantApprovalResponseAvroModel.getRestaurantId().toString())
                                .orderId(restaurantApprovalResponseAvroModel.getOrderId().toString())
                                .createdAt(restaurantApprovalResponseAvroModel.getCreatedAt())
                                .orderApprovalStatus(
                                                com.food.ordering.system.domain.valueobject.OrderApprovalStatus.valueOf(
                                                                restaurantApprovalResponseAvroModel
                                                                                .getOrderApprovalStatus().name()))
                                .failureMessages(restaurantApprovalResponseAvroModel.getFailureMessages())
                                .build();
        }
}
