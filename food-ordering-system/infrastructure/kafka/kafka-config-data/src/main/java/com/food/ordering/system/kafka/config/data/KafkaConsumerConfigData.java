package com.food.ordering.system.kafka.config.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Configuration data class for Kafka consumer properties.
 * <p>
 * This class is used to map properties prefixed with "kafka-consumer-config"
 * from the application's configuration files.
 * </p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-config")
public class KafkaConsumerConfigData {
    /**
     * The class name of the key deserializer.
     */
    private String keyDeserializer;

    /**
     * The class name of the value deserializer.
     */
    private String valueDeserializer;

    /**
     * The strategy to reset offsets (e.g., "earliest", "latest").
     */
    private String autoOffsetReset;

    /**
     * The property key for specific Avro reader.
     */
    private String specificAvroReaderKey;

    /**
     * The property value for specific Avro reader.
     */
    private String specificAvroReader;

    /**
     * Whether to enable batch listener mode.
     */
    private Boolean batchListener;

    /**
     * Whether the consumer should start automatically.
     */
    private Boolean autoStartup;

    /**
     * The concurrency level for the consumer.
     */
    private Integer concurrencyLevel;

    /**
     * The session timeout in milliseconds.
     */
    private Integer sessionTimeoutMs;

    /**
     * The heartbeat interval in milliseconds.
     */
    private Integer heartbeatIntervalMs;

    /**
     * The maximum poll interval in milliseconds.
     */
    private Integer maxPollIntervalMs;

    /**
     * The poll timeout in milliseconds.
     */
    private Long pollTimeoutMs;

    /**
     * The maximum number of records returned in a single poll.
     */
    private Integer maxPollRecords;

    /**
     * The default maximum partition fetch bytes.
     */
    private Integer maxPartitionFetchBytesDefault;

    /**
     * The boost factor for maximum partition fetch bytes.
     */
    private Integer maxPartitionFetchBytesBoostFactor;
}
