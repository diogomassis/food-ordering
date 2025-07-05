package com.food.ordering.system.domain.event.publisher;

import com.food.ordering.system.domain.event.IDomainEvent;

@SuppressWarnings("rawtypes")
public interface IDomainEventPublisher<T extends IDomainEvent> {
    void publish(T domainEvent);
}
