package com.food.ordering.system.order.service.messaging.listener.kafka;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.consumer.IKafkaConsumer;
import com.food.ordering.system.kafka.order.avro.model.OrderApprovalStatus;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval.IRestaurantApprovalResponseMessageListener;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Kafka listener for restaurant approval response messages.
 * This class listens to restaurant approval response events from Kafka and
 * delegates processing
 * to the appropriate domain service based on approval status.
 */
@Slf4j
@Component
public class RestaurantApprovalResponseKafkaListener
        implements IKafkaConsumer<RestaurantApprovalResponseAvroModel> {
    /**
     * Domain service for handling restaurant approval response messages.
     */
    private final IRestaurantApprovalResponseMessageListener restaurantApprovalResponseMessageListener;

    /**
     * Mapper for converting Avro models to domain DTOs.
     */
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    /**
     * Constructs a RestaurantApprovalResponseKafkaListener with required
     * dependencies.
     *
     * @param restaurantApprovalResponseMessageListener the domain service for
     *                                                  restaurant approval
     *                                                  responses
     * @param orderMessagingDataMapper                  the mapper for Avro to
     *                                                  domain conversion
     */
    public RestaurantApprovalResponseKafkaListener(
            IRestaurantApprovalResponseMessageListener restaurantApprovalResponseMessageListener,
            OrderMessagingDataMapper orderMessagingDataMapper) {
        this.restaurantApprovalResponseMessageListener = restaurantApprovalResponseMessageListener;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.restaurant-approval-consumer-group-id}", topics = "${order-service.restaurant-approval-response-topic-name}")
    public void receive(@Payload List<RestaurantApprovalResponseAvroModel> messages,
            @Header(KafkaHeaders.RECEIVED_KEY) List<Long> keys,
            @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of restaurant approval responses received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(restaurantApprovalResponseAvroModel -> {
            try {
                if (OrderApprovalStatus.APPROVED == restaurantApprovalResponseAvroModel.getOrderApprovalStatus()) {
                    log.info("Processing approved order for order id: {}",
                            restaurantApprovalResponseAvroModel.getOrderId());
                    restaurantApprovalResponseMessageListener.orderApproved(orderMessagingDataMapper
                            .approvalResponseAvroModelToApprovalResponse(restaurantApprovalResponseAvroModel));
                } else if (OrderApprovalStatus.REJECTED == restaurantApprovalResponseAvroModel
                        .getOrderApprovalStatus()) {
                    log.info("Processing rejected order for order id: {}, with failure messages: {}",
                            restaurantApprovalResponseAvroModel.getOrderId(),
                            String.join(Order.FAILURE_MESSAGE_DELIMITER,
                                    restaurantApprovalResponseAvroModel.getFailureMessages()));
                    restaurantApprovalResponseMessageListener.orderRejected(orderMessagingDataMapper
                            .approvalResponseAvroModelToApprovalResponse(restaurantApprovalResponseAvroModel));
                }
            } catch (OptimisticLockingFailureException e) {
                // NO-OP for optimistic lock. This means another thread finished the work, do
                // not throw error to prevent reading the data from kafka again!
                log.error(
                        "Caught optimistic locking exception in RestaurantApprovalResponseKafkaListener for order id: {}",
                        restaurantApprovalResponseAvroModel.getOrderId().toString());
            } catch (OrderNotFoundException e) {
                // NO-OP for OrderNotFoundException
                log.error("No order found for order id: {}",
                        restaurantApprovalResponseAvroModel.getOrderId().toString());
            }
        });
    }
}
