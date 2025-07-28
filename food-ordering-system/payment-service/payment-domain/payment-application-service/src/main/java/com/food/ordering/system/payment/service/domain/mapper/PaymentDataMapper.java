package com.food.ordering.system.payment.service.domain.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.food.ordering.system.payment.service.domain.entity.Payment;

/**
 * Mapper class for converting payment request models to payment entities.
 */
@Component
public class PaymentDataMapper {
    /**
     * Converts a PaymentRequest model to a Payment entity.
     *
     * @param paymentRequest The payment request to convert
     * @return The corresponding Payment entity
     */
    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }
}
