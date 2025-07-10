package com.food.ordering.system.kafka.config.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Configuration data class for Kafka producer properties.
 * <p>
 * This class is used to map properties prefixed with "kafka-producer-config"
 * from the application's configuration files.
 * </p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-producer-config")
public class KafkaProducerConfigData {
    /**
     * The class name of the key serializer.
     */
    private String keySerializerClass;

    /**
     * The class name of the value serializer.
     */
    private String valueSerializerClass;

    /**
     * The compression type for producer messages (e.g., "gzip", "snappy").
     */
    private String compressionType;

    /**
     * The number of acknowledgments the producer requires.
     */
    private String acks;

    /**
     * The batch size for producer messages.
     */
    private Integer batchSize;

    /**
     * The boost factor for batch size.
     */
    private Integer batchSizeBoostFactor;

    /**
     * The linger time in milliseconds before sending a batch.
     */
    private Integer lingerMs;

    /**
     * The request timeout in milliseconds.
     */
    private Integer requestTimeoutMs;

    /**
     * The number of retry attempts for failed sends.
     */
    private Integer retryCount;
}
