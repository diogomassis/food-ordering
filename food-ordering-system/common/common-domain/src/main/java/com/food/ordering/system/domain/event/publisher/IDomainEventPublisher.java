package com.food.ordering.system.domain.event.publisher;

import com.food.ordering.system.domain.event.IDomainEvent;

/**
 * Interface for publishing domain events.
 *
 * @param <T> the type of domain event to be published, must extend
 *            {@link IDomainEvent}
 */
@SuppressWarnings("rawtypes")
public interface IDomainEventPublisher<T extends IDomainEvent> {
    /**
     * Publishes the given domain event.
     *
     * @param domainEvent the domain event to publish
     */
    void publish(T domainEvent);
}
