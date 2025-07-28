package com.food.ordering.system.domain.event;

/**
 * Represents a domain event in the system.
 * <p>
 * Domain events are used to capture and communicate significant occurrences
 * within the domain.
 * Implementations of this interface should define the specific event details
 * and payload.
 *
 * @param <T> the type of the entity or aggregate root associated with the event
 */
public interface IDomainEvent<T> {
    /**
     * Fires the domain event, triggering any associated handlers or listeners.
     */
    void fire();
}
