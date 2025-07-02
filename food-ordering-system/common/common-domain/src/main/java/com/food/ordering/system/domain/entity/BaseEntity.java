package com.food.ordering.system.domain.entity;

import java.util.Objects;

/**
 * Abstract base class for entities with a generic identifier.
 * <p>
 * This class provides a common structure for entities by encapsulating an ID
 * field,
 * along with standard implementations of {@code equals} and {@code hashCode}
 * methods
 * based on the entity's identifier.
 *
 * @param <ID> the type of the identifier for the entity
 */
public abstract class BaseEntity<ID> {
    /**
     * Unique identifier for the entity.
     * 
     * @param <ID> the type of the identifier, typically a value object or primitive
     *             type.
     */
    private ID id;

    /**
     * Retrieves the unique identifier of this entity.
     *
     * @return the ID of the entity
     */
    public ID getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this entity.
     *
     * @param id the unique identifier to set
     */
    public void setId(ID id) {
        this.id = id;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The equality is based on the runtime class and the value of the {@code id}
     * field.
     * Two {@code BaseEntity} objects are considered equal if they are of the same
     * class
     * and have the same {@code id}.
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this object is the same as the obj argument;
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return id.equals(that.id);
    }

    /**
     * Returns a hash code value for the entity based on its identifier.
     * This implementation uses {@link Objects#hashCode(Object)} to generate
     * the hash code from the {@code id} field, ensuring consistent behavior
     * with {@link #equals(Object)} when entities are used in hash-based
     * collections.
     *
     * @return the hash code value for this entity
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
