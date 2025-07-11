package com.food.ordering.system.kafka.producer.service;

import java.io.Serializable;
import java.util.function.BiConsumer;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.support.SendResult;

/**
 * Interface for a Kafka producer service.
 *
 * @param <K> the type of the key, must be Serializable
 * @param <V> the type of the value, must extend SpecificRecordBase
 */
public interface IKafkaProducer<K extends Serializable, V extends SpecificRecordBase> {
    /**
     * Sends a message to the specified Kafka topic.
     *
     * @param topicName the name of the Kafka topic
     * @param key       the key of the message
     * @param message   the message to send
     * @param callback  a callback to handle the result or error of the send
     *                  operation
     */
    void send(String topicName, K key, V message, BiConsumer<SendResult<K, V>, Throwable> callback);
}
