package com.food.ordering.system.order.service.messaging.publisher.kafka;

import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.kafka.producer.service.IKafkaProducer;
import com.food.ordering.system.order.service.domain.config.OrderServiceConfigData;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Kafka message publisher for sending restaurant approval requests when an
 * order is paid.
 */
@Slf4j
@Component
public class PayOrderKafkaMessagePublisher implements OrderPaidRestaurantRequestMessagePublisher {
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
    private final IKafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer;

    /**
     * Constructs a PayOrderKafkaMessagePublisher with required dependencies.
     *
     * @param orderMessagingDataMapper Mapper for domain events to Avro models
     * @param orderServiceConfigData   Configuration data for order service
     * @param orderKafkaMessageHelper  Helper for Kafka message operations
     * @param kafkaProducer            Kafka producer for sending messages
     */
    public PayOrderKafkaMessagePublisher(OrderMessagingDataMapper orderMessagingDataMapper,
            OrderServiceConfigData orderServiceConfigData, OrderKafkaMessageHelper orderKafkaMessageHelper,
            IKafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.orderServiceConfigData = orderServiceConfigData;
        this.orderKafkaMessageHelper = orderKafkaMessageHelper;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void publish(OrderPaidEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();
        log.info("Received OrderPaidEvent for order id: {}", orderId);
        try {
            RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel = orderMessagingDataMapper
                    .orderPaidEventToRestaurantApprovalRequestAvroModel(domainEvent);
            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(), orderId,
                    restaurantApprovalRequestAvroModel,
                    orderKafkaMessageHelper.getKafkaCallback(orderServiceConfigData.getPaymentRequestTopicName(),
                            restaurantApprovalRequestAvroModel, orderId, "RestaurantApprovalRequestAvroModel"));
            log.info("RestaurantApprovalRequestAvroModel sent to Kafka for order id: {}",
                    restaurantApprovalRequestAvroModel.getOrderId().toString());
        } catch (Exception e) {
            log.error(
                    "Error while sending RestaurantApprovalRequestAvroModel message to kafka with order id: {} error: {}",
                    orderId, e.getMessage());
        }
    }
}
