package com.food.ordering.system.order.service.domain;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * Publishes domain events using Spring's {@link ApplicationEventPublisher}.
 * <p>
 * This class implements {@link ApplicationEventPublisherAware} to receive the
 * event publisher and {@link IDomainEventPublisher} to publish
 * {@link OrderCreatedEvent} events.
 * </p>
 */
@Slf4j
@Component
public class ApplicationDomainEventPublisher
        implements ApplicationEventPublisherAware, IDomainEventPublisher<OrderCreatedEvent> {

    /**
     * The Spring application event publisher used to publish domain events.
     */
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * Sets the {@link ApplicationEventPublisher} instance.
     *
     * @param applicationEventPublisher the event publisher to use
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Publishes the given {@link OrderCreatedEvent} using the application event
     * publisher.
     *
     * @param domainEvent the event to publish
     */
    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        this.applicationEventPublisher.publishEvent(domainEvent);
        log.info("OrderCreatedEvent is published with order id {}",
                domainEvent.getOrder().getId().getValue().toString());
    }
}
