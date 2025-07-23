package com.food.ordering.system.payment.service.domain;

import java.util.List;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.CreditEntry;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentFailedEvent;

public interface IPaymentDomainService {
    PaymentEvent validateAndInitiatePayment(Payment payment, CreditEntry creditEntry,
            List<CreditHistory> creditHistories, List<String> failureMessages,
            IDomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher,
            IDomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);

    PaymentEvent validateAndCancelPayment(Payment payment, CreditEntry creditEntry,
            List<CreditHistory> creditHistories,
            List<String> failureMessages,
            IDomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
            IDomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);
}
