package com.food.ordering.system.kafka.config.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Configuration data class for general Kafka properties.
 * <p>
 * This class is used to map properties prefixed with "kafka-config"
 * from the application's configuration files.
 * </p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfigData {
    /**
     * The Kafka bootstrap servers.
     */
    private String bootstrapServers;

    /**
     * The property key for the schema registry URL.
     */
    private String schemaRegistryUrlKey;

    /**
     * The schema registry URL.
     */
    private String schemaRegistryUrl;

    /**
     * The default number of partitions for topics.
     */
    private Integer numOfPartitions;

    /**
     * The replication factor for topics.
     */
    private Short replicationFactor;
}
