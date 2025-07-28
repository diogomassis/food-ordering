package com.food.ordering.system.payment.service.domain;

import org.springframework.stereotype.Service;

import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.food.ordering.system.payment.service.domain.ports.input.message.listener.IPaymentRequestMessageListener;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.IPaymentCancelledMessagePublisher;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.IPaymentCompletedMessagePublisher;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.IPaymentFailedMessagePublisher;

import lombok.extern.slf4j.Slf4j;

/**
 * Listener for handling payment request messages.
 * Responsible for processing payment completion and cancellation requests,
 * and publishing corresponding payment events.
 */
@Slf4j
@Service
public class PaymentRequestMessageListener implements IPaymentRequestMessageListener {
    /**
     * Helper for handling payment request persistence logic.
     */
    private final PaymentRequestHelper paymentRequestHelper;

    /**
     * Publisher for completed payment messages.
     */
    private final IPaymentCompletedMessagePublisher paymentCompletedMessagePublisher;

    /**
     * Publisher for cancelled payment messages.
     */
    private final IPaymentCancelledMessagePublisher paymentCancelledMessagePublisher;

    /**
     * Publisher for failed payment messages.
     */
    private final IPaymentFailedMessagePublisher paymentFailedMessagePublisher;

    /**
     * Constructs a new PaymentRequestMessageListener with required dependencies.
     *
     * @param paymentRequestHelper             Helper for payment request
     *                                         persistence
     * @param paymentCompletedMessagePublisher Publisher for completed payment
     *                                         messages
     * @param paymentCancelledMessagePublisher Publisher for cancelled payment
     *                                         messages
     * @param paymentFailedMessagePublisher    Publisher for failed payment messages
     */
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

    /**
     * Publishes the given payment event and logs the operation.
     *
     * @param paymentEvent The payment event to be published
     */
    private void fireEvent(PaymentEvent paymentEvent) {
        log.info("Publishing payment event with payment id: {} and order id: {}",
                paymentEvent.getPayment().getId().getValue(), paymentEvent.getPayment().getOrderId().getValue());
        paymentEvent.fire();
    }
}
