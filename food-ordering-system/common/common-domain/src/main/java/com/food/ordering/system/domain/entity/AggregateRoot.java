package com.food.ordering.system.domain.entity;

/**
 * Represents the root entity of an aggregate in the domain-driven design (DDD)
 * pattern.
 * <p>
 * An AggregateRoot is a special type of {@link BaseEntity} that serves as the
 * entry point
 * for accessing and modifying the aggregate. All changes to the aggregate
 * should be made
 * through the aggregate root to ensure consistency and enforce invariants.
 *
 * @param <ID> the type of the identifier of the aggregate root
 */
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {

}
