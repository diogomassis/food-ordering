package com.food.ordering.system.payment.service.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Configuration properties for the payment service.
 * Holds topic names for payment request and response messaging.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "payment-service")
public class PaymentServiceConfigData {
    /**
     * Name of the topic for payment requests.
     */
    private String paymentRequestTopicName;

    /**
     * Name of the topic for payment responses.
     */
    private String paymentResponseTopicName;
}
