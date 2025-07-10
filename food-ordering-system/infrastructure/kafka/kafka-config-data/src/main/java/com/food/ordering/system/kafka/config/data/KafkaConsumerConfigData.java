package com.food.ordering.system.kafka.config.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-config")
public class KafkaConsumerConfigData {

}
