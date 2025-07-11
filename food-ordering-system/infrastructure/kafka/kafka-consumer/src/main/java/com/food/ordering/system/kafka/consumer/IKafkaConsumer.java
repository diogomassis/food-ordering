package com.food.ordering.system.kafka.consumer;

import java.util.List;

import org.apache.avro.specific.SpecificRecordBase;

/**
 * Interface for a Kafka consumer that processes messages of a specific Avro
 * type.
 *
 * @param <T> the type of the message, must extend SpecificRecordBase
 */
public interface IKafkaConsumer<T extends SpecificRecordBase> {
    /**
     * Receives and processes a batch of messages from Kafka.
     *
     * @param messages   the list of messages received
     * @param keys       the list of message keys
     * @param partitions the list of partition numbers
     * @param offsets    the list of message offsets
     */
    void receive(List<T> messages, List<Long> keys, List<Integer> partitions, List<Long> offsets);
}
