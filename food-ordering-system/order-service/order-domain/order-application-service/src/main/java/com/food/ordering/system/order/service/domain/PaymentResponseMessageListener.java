package com.food.ordering.system.order.service.domain;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.payment.IPaymentResponseMessageListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Listener implementation for handling payment response messages.
 * <p>
 * This service listens for payment completion and cancellation events,
 * and processes them accordingly. It implements the
 * {@link IPaymentResponseMessageListener}
 * interface to handle payment-related callbacks.
 * </p>
 */
@Slf4j
@Validated
@Service
public class PaymentResponseMessageListener implements IPaymentResponseMessageListener {
    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'paymentCompleted'");
    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'paymentCancelled'");
    }
}
