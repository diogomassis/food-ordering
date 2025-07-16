package com.food.ordering.system.order.service.messaging.publisher.kafka;

import java.util.function.BiConsumer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderKafkaMessageHelper {
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
