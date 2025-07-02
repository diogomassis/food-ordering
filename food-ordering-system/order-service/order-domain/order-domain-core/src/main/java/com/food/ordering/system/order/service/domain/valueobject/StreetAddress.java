package com.food.ordering.system.order.service.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a value object for a street address, containing the street name,
 * postal code, city, and a unique identifier.
 * <p>
 * This class is immutable and provides value-based equality based on street,
 * postal code, and city.
 * </p>
 */
public class StreetAddress {
    /**
     * Unique identifier for the street address.
     */
    private final UUID id;
    /**
     * The street name of the address.
     */
    private final String street;
    /**
     * The postal code associated with the street address.
     * This value is immutable and uniquely identifies the postal region for the
     * address.
     */
    private final String postalCode;
    /**
     * The city associated with the street address.
     */
    private final String city;

    /**
     * Constructs a new {@code StreetAddress} instance with the specified unique
     * identifier,
     * street name, postal code, and city.
     *
     * @param id         the unique identifier for the street address
     * @param street     the name of the street
     * @param postalCode the postal code of the address
     * @param city       the city of the address
     */
    public StreetAddress(UUID id, String street, String postalCode, String city) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    /**
     * Returns the unique identifier (UUID) associated with this street address.
     *
     * @return the UUID of the street address
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the street name of this address.
     *
     * @return the street name as a String
     */
    public String getStreet() {
        return street;
    }

    /**
     * Retrieves the postal code associated with this street address.
     *
     * @return the postal code as a {@code String}
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Returns the city associated with this street address.
     *
     * @return the city as a {@code String}
     */
    public String getCity() {
        return city;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two {@code StreetAddress} objects are considered equal if their
     * {@code street}, {@code postalCode}, and {@code city} fields are all equal.
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
        StreetAddress that = (StreetAddress) obj;
        return street.equals(that.street) && postalCode.equals(that.postalCode) && city.equals(that.city);
    }

    /**
     * Generates a hash code for this {@code StreetAddress} based on its street,
     * postal code, and city fields.
     * This method is consistent with {@link #equals(Object)} and is used for
     * efficient storage in hash-based collections.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(street, postalCode, city);
    }
}
