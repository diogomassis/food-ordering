package com.food.ordering.system.order.service.messaging.publisher.kafka;

import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.producer.service.IKafkaProducer;
import com.food.ordering.system.order.service.domain.config.OrderServiceConfigData;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Kafka message publisher for sending payment requests when an order is
 * cancelled.
 */
@Slf4j
@Component
public class CancelOrderKafkaMessagePublisher implements OrderCancelledPaymentRequestMessagePublisher {
    /**
     * Mapper for converting domain events to Avro models.
     */
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    /**
     * Configuration data for the order service.
     */
    private final OrderServiceConfigData orderServiceConfigData;

    /**
     * Helper for Kafka message operations.
     */
    private final OrderKafkaMessageHelper orderKafkaMessageHelper;

    /**
     * Kafka producer for sending messages.
     */
    private final IKafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;

    /**
     * Constructs a CancelOrderKafkaMessagePublisher with required dependencies.
     *
     * @param orderMessagingDataMapper Mapper for domain events to Avro models
     * @param orderServiceConfigData   Configuration data for order service
     * @param orderKafkaMessageHelper  Helper for Kafka message operations
     * @param kafkaProducer            Kafka producer for sending messages
     */
    public CancelOrderKafkaMessagePublisher(OrderMessagingDataMapper orderMessagingDataMapper,
            OrderServiceConfigData orderServiceConfigData, OrderKafkaMessageHelper orderKafkaMessageHelper,
            IKafkaProducer<String, PaymentRequestAvroModel> kafkaProducer) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.orderServiceConfigData = orderServiceConfigData;
        this.orderKafkaMessageHelper = orderKafkaMessageHelper;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void publish(OrderCancelledEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();
        log.info("Received OrderCancelledEvent for order id: {}", orderId);
        try {
            PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
                    .orderCancelledEventToPaymentRequestAvroModel(domainEvent);
            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(), orderId, paymentRequestAvroModel,
                    orderKafkaMessageHelper.getKafkaCallback(orderServiceConfigData.getPaymentRequestTopicName(),
                            paymentRequestAvroModel, orderId, "PaymentRequestAvroModel"));
            log.info("PaymentRequestAvroModel sent to Kafka for order id: {}",
                    paymentRequestAvroModel.getOrderId().toString());
        } catch (Exception e) {
            log.error("Error while sending PaymentRequestAvroModel message to kafka with order id: {} error: {}",
                    orderId, e.getMessage());
        }
    }
}
