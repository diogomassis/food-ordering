package com.food.ordering.system.order.service.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Configuration data class for order service Kafka topic names.
 * This class binds configuration properties with the prefix "order-service"
 * from application properties to Java fields for use throughout the order
 * service.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "order-service")
public class OrderServiceConfigData {
    /**
     * The Kafka topic name for publishing payment requests.
     * Used when the order service needs to request payment processing.
     */
    private String paymentRequestTopicName;

    /**
     * The Kafka topic name for consuming payment responses.
     * Used to receive payment processing results from the payment service.
     */
    private String paymentResponseTopicName;

    /**
     * The Kafka topic name for publishing restaurant approval requests.
     * Used when the order service needs to request restaurant approval for orders.
     */
    private String restaurantApprovalRequestTopicName;

    /**
     * The Kafka topic name for consuming restaurant approval responses.
     * Used to receive restaurant approval results from the restaurant service.
     */
    private String restaurantApprovalResponseTopicName;
}
