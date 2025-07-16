package com.food.ordering.system.order.service.messaging.listener.kafka;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.consumer.IKafkaConsumer;
import com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.kafka.order.avro.model.PaymentStatus;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.payment.IPaymentResponseMessageListener;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Kafka listener for payment response messages.
 * This class listens to payment response events from Kafka and delegates
 * processing
 * to the appropriate domain service based on payment status.
 */
@Slf4j
@Component
public class PaymentResponseKafkaListener implements IKafkaConsumer<PaymentResponseAvroModel> {
    /**
     * Domain service for handling payment response messages.
     */
    private final IPaymentResponseMessageListener paymentResponseMessageListener;

    /**
     * Mapper for converting Avro models to domain DTOs.
     */
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    /**
     * Constructs a PaymentResponseKafkaListener with required dependencies.
     *
     * @param paymentResponseMessageListener the domain service for payment
     *                                       responses
     * @param orderMessagingDataMapper       the mapper for Avro to domain
     *                                       conversion
     */
    public PaymentResponseKafkaListener(IPaymentResponseMessageListener paymentResponseMessageListener,
            OrderMessagingDataMapper orderMessagingDataMapper) {
        this.paymentResponseMessageListener = paymentResponseMessageListener;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.payment-consumer-group-id}", topics = "${order-service.payment-response-topic-name}")
    public void receive(@Payload List<PaymentResponseAvroModel> messages,
            @Header(KafkaHeaders.RECEIVED_KEY) List<Long> keys,
            @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());
        messages.forEach(paymentResponseAvroModel -> {
            try {
                if (PaymentStatus.COMPLETED == paymentResponseAvroModel.getPaymentStatus()) {
                    log.info("Processing successful payment for order id: {}", paymentResponseAvroModel.getOrderId());
                    paymentResponseMessageListener.paymentCompleted(orderMessagingDataMapper
                            .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
                } else if (PaymentStatus.CANCELLED == paymentResponseAvroModel.getPaymentStatus() ||
                        PaymentStatus.FAILED == paymentResponseAvroModel.getPaymentStatus()) {
                    log.info("Processing unsuccessful payment for order id: {}", paymentResponseAvroModel.getOrderId());
                    paymentResponseMessageListener.paymentCancelled(orderMessagingDataMapper
                            .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
                }
            } catch (OptimisticLockingFailureException e) {
                // NO-OP for optimistic lock. This means another thread finished the work, do
                // not throw error to prevent reading the data from kafka again!
                log.error("Caught optimistic locking exception in PaymentResponseKafkaListener for order id: {}",
                        paymentResponseAvroModel.getOrderId().toString());
            } catch (OrderNotFoundException e) {
                // NO-OP for OrderNotFoundException
                log.error("No order found for order id: {}", paymentResponseAvroModel.getOrderId().toString());
            }
        });
    }
}
