package com.food.ordering.system.kafka.producer.exception;

/**
 * Exception thrown when an error occurs in the Kafka producer.
 */
public class KafkaProducerException extends RuntimeException {
    /**
     * Constructs a new KafkaProducerException with the specified detail message.
     *
     * @param message the detail message
     */
    public KafkaProducerException(String message) {
        super(message);
    }
}
