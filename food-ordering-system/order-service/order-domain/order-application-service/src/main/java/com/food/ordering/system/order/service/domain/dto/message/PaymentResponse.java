package com.food.ordering.system.order.service.domain.dto.message;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.food.ordering.system.domain.valueobject.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Data transfer object representing the response of a payment operation.
 */
@Getter
@Builder
@AllArgsConstructor
public class PaymentResponse {
    /**
     * Unique identifier for the payment response.
     */
    private String id;

    /**
     * Identifier for the saga process associated with this payment.
     */
    private String sagaId;

    /**
     * Identifier of the order related to the payment.
     */
    private String orderId;

    /**
     * Identifier of the payment transaction.
     */
    private String paymentId;

    /**
     * Identifier of the customer who made the payment.
     */
    private String customerId;

    /**
     * The price amount involved in the payment.
     */
    private BigDecimal price;

    /**
     * The timestamp when the payment response was created.
     */
    private Instant createdAt;

    /**
     * The status of the payment.
     */
    private PaymentStatus paymentStatus;

    /**
     * List of failure messages if the payment failed.
     */
    private List<String> failureMessages;
}
