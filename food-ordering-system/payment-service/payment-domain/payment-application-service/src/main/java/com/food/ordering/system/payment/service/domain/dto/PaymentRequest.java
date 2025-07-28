package com.food.ordering.system.payment.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

import com.food.ordering.system.domain.valueobject.PaymentOrderStatus;

/**
 * Data transfer object representing a payment request.
 */
@Getter
@Builder
@AllArgsConstructor
public class PaymentRequest {
    /**
     * Unique identifier for the payment request.
     */
    private String id;

    /**
     * Identifier for the saga process.
     */
    private String sagaId;

    /**
     * Identifier for the associated order.
     */
    private String orderId;

    /**
     * Identifier for the customer making the payment.
     */
    private String customerId;

    /**
     * Payment amount.
     */
    private BigDecimal price;

    /**
     * Timestamp when the payment request was created.
     */
    private Instant createdAt;

    /**
     * Status of the payment order.
     */
    private PaymentOrderStatus paymentOrderStatus;

    /**
     * Sets the payment order status.
     *
     * @param paymentOrderStatus The new payment order status
     */
    public void setPaymentOrderStatus(PaymentOrderStatus paymentOrderStatus) {
        this.paymentOrderStatus = paymentOrderStatus;
    }
}
