package com.food.ordering.system.domain.valueobject;

import java.util.Objects;

/**
 * Abstract base class representing an identifier with a generic value type.
 * <p>
 * This class is intended to be extended by domain-specific ID classes,
 * providing a strongly-typed identifier for entities.
 * </p>
 *
 * @param <T> the type of the identifier value
 */
public abstract class BaseId<T> {
    /**
     * The unique identifier value for the entity.
     * 
     * @param <T> the type of the identifier (e.g., UUID, Long, String)
     */
    private final T value;

    /**
     * Constructs a new BaseId with the specified value.
     *
     * @param value the identifier value to be associated with this BaseId
     */
    protected BaseId(T value) {
        this.value = value;
    }

    /**
     * Retrieves the value of the identifier.
     *
     * @return the value of the identifier of type T
     */
    public T getValue() {
        return value;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two {@code BaseId} objects are considered equal if they are of the same class
     * and their {@code value} fields are equal.
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
        BaseId<?> baseId = (BaseId<?>) obj;
        return value.equals(baseId.value);
    }

    /**
     * Returns the hash code value for this object based on its {@code value} field.
     * This implementation uses {@link Objects#hashCode(Object)} to compute the hash
     * code.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
