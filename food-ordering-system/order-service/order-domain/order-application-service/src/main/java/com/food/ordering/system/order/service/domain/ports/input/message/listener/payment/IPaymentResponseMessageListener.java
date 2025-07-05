package com.food.ordering.system.order.service.domain.ports.input.message.listener.payment;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;

/**
 * Listener interface for handling payment response messages.
 * Provides methods to process completed and cancelled payment events.
 */
public interface IPaymentResponseMessageListener {
    /**
     * Handles the event when a payment is completed.
     *
     * @param paymentResponse the payment response data
     */
    void paymentCompleted(PaymentResponse paymentResponse);

    /**
     * Handles the event when a payment is cancelled.
     *
     * @param paymentResponse the payment response data
     */
    void paymentCancelled(PaymentResponse paymentResponse);
}
