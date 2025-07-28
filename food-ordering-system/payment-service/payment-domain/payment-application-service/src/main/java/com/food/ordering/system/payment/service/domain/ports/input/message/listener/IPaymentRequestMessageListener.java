package com.food.ordering.system.payment.service.domain.ports.input.message.listener;

import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;

/**
 * Listener interface for handling payment request messages.
 * Defines operations for completing and cancelling payments.
 */
public interface IPaymentRequestMessageListener {
    /**
     * Handles the completion of a payment request.
     *
     * @param paymentRequest The payment request to complete
     */
    void completePayment(PaymentRequest paymentRequest);

    /**
     * Handles the cancellation of a payment request.
     *
     * @param paymentRequest The payment request to cancel
     */
    void cancelPayment(PaymentRequest paymentRequest);
}
