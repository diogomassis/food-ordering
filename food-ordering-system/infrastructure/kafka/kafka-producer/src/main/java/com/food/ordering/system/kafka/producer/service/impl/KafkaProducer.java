package com.food.ordering.system.kafka.producer.service.impl;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.producer.exception.KafkaProducerException;
import com.food.ordering.system.kafka.producer.service.IKafkaProducer;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

/**
 * Kafka producer implementation for sending messages to Kafka topics.
 *
 * @param <K> the type of the key, must be Serializable
 * @param <V> the type of the value, must extend SpecificRecordBase
 */
@Slf4j
@Component
public class KafkaProducer<K extends Serializable, V extends SpecificRecordBase> implements IKafkaProducer<K, V> {
    /**
     * KafkaTemplate used to send messages to Kafka.
     */
    private final KafkaTemplate<K, V> kafkaTemplate;

    /**
     * Constructs a new KafkaProducer with the given KafkaTemplate.
     *
     * @param kafkaTemplate the KafkaTemplate to use for sending messages
     */
    public KafkaProducer(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message, BiConsumer<SendResult<K, V>, Throwable> callback) {
        try {
            log.info("Sending message={} to topic={}", message, topicName);
            CompletableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(topicName, key, message);
            kafkaResultFuture.whenComplete(callback);
        } catch (KafkaException e) {
            log.error("Error on kafka producer with key: {}, message: {} and exception: {}", key, message,
                    e.getMessage());
            throw new KafkaProducerException("Error on kafka producer with key: " + key + " and message: " + message);
        }
    }

    /**
     * Closes the Kafka producer and releases resources.
     * This method is called before the bean is destroyed.
     */
    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            log.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }
}
