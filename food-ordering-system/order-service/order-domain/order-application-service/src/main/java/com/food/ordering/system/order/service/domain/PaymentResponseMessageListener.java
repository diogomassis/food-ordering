package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.payment.IPaymentResponseMessageListener;

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
