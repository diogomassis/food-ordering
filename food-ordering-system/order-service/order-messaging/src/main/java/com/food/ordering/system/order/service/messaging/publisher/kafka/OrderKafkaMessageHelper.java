package com.food.ordering.system.order.service.messaging.publisher.kafka;

import java.util.function.BiConsumer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Helper class for handling Kafka message publishing callbacks for
 * order-related events.
 */
@Slf4j
@Component
public class OrderKafkaMessageHelper {

    /**
     * Returns a Kafka callback {@link BiConsumer} to handle the result of sending a
     * message.
     *
     * @param requestTopicName     the name of the Kafka topic to which the message
     *                             is sent
     * @param requestAvroModel     the Avro model representing the message payload
     * @param orderId              the ID of the order associated with the message
     * @param requestAvroModelName the name of the Avro model type
     * @param <T>                  the type of the Avro model
     * @return a {@link BiConsumer} that logs success or error information after
     *         message publishing
     */
    public <T> BiConsumer<SendResult<String, T>, Throwable> getKafkaCallback(
            String requestTopicName, T requestAvroModel, String orderId, String requestAvroModelName) {
        return (sendResult, throwable) -> {
            if (throwable != null) {
                log.error("Error while sending {} message {} to topic {}",
                        requestAvroModelName, requestAvroModel.toString(), requestTopicName);
            } else if (sendResult != null) {
                RecordMetadata recordMetadata = sendResult.getRecordMetadata();
                log.info(
                        "Received successful response from Kafka for order id: {} Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        orderId, recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(),
                        recordMetadata.timestamp());
            }
        };
    }
}
