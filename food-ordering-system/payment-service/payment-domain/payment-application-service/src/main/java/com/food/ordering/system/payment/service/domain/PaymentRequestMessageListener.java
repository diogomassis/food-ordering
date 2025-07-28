package com.food.ordering.system.payment.service.domain;

import org.springframework.stereotype.Service;

import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.food.ordering.system.payment.service.domain.ports.input.message.listener.IPaymentRequestMessageListener;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.IPaymentCancelledMessagePublisher;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.IPaymentCompletedMessagePublisher;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.IPaymentFailedMessagePublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentRequestMessageListener implements IPaymentRequestMessageListener {
    private final PaymentRequestHelper paymentRequestHelper;
    private final IPaymentCompletedMessagePublisher paymentCompletedMessagePublisher;
    private final IPaymentCancelledMessagePublisher paymentCancelledMessagePublisher;
    private final IPaymentFailedMessagePublisher paymentFailedMessagePublisher;

    public PaymentRequestMessageListener(
            PaymentRequestHelper paymentRequestHelper,
            IPaymentCompletedMessagePublisher paymentCompletedMessagePublisher,
            IPaymentCancelledMessagePublisher paymentCancelledMessagePublisher,
            IPaymentFailedMessagePublisher paymentFailedMessagePublisher) {
        this.paymentRequestHelper = paymentRequestHelper;
        this.paymentCompletedMessagePublisher = paymentCompletedMessagePublisher;
        this.paymentCancelledMessagePublisher = paymentCancelledMessagePublisher;
        this.paymentFailedMessagePublisher = paymentFailedMessagePublisher;
    }

    @Override
    public void completePayment(PaymentRequest paymentRequest) {
        PaymentEvent paymentEvent = paymentRequestHelper.persistPayment(paymentRequest);
        fireEvent(paymentEvent);
    }

    @Override
    public void cancelPayment(PaymentRequest paymentRequest) {
        PaymentEvent paymentEvent = paymentRequestHelper.persistCancelPayment(paymentRequest);
        fireEvent(paymentEvent);
    }

    private void fireEvent(PaymentEvent paymentEvent) {
        log.info("Publishing payment event with payment id: {} and order id: {}",
                paymentEvent.getPayment().getId().getValue(), paymentEvent.getPayment().getOrderId().getValue());
        paymentEvent.fire();
    }
}
