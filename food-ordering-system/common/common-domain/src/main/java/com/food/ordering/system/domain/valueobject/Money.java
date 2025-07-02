package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Represents a monetary value with fixed scale and rounding.
 * <p>
 * This class encapsulates a {@link BigDecimal} amount and provides utility
 * methods
 * for common monetary operations such as addition, subtraction, multiplication,
 * and comparison. All operations ensure the amount is scaled to two decimal
 * places
 * using {@link RoundingMode#HALF_EVEN}.
 * </p>
 * <p>
 * Instances of this class are immutable.
 * </p>
 */
public class Money {
    /**
     * Represents the monetary value associated with this object.
     * This field holds the amount as a {@link BigDecimal} to ensure precision in
     * financial calculations.
     * It is immutable and should not be modified after initialization.
     */
    private final BigDecimal amount;

    /**
     * A constant representing a monetary value of zero.
     * This can be used as a default or initial value for monetary calculations.
     */
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    /**
     * Constructs a new {@code Money} instance with the specified amount.
     *
     * @param amount the monetary value represented by this {@code Money} object.
     */
    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Returns the monetary amount represented by this {@code Money} object.
     *
     * @return the amount as a {@link BigDecimal}
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Checks if the monetary amount is greater than zero.
     *
     * @return {@code true} if the amount is not {@code null} and greater than zero;
     *         {@code false} otherwise.
     */
    public boolean isGreaterThanZero() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * Checks if the monetary amount of this {@code Money} instance is greater than
     * the amount of the specified {@code Money} instance.
     *
     * @param money the {@code Money} instance to compare with
     * @return {@code true} if this amount is not {@code null} and greater than the
     *         specified amount; {@code false} otherwise
     */
    public boolean isGreaterThan(Money money) {
        return amount != null && amount.compareTo(money.getAmount()) > 0;
    }

    /**
     * Adds the specified {@code Money} amount to this {@code Money} instance and
     * returns a new {@code Money} object
     * representing the sum. The result is scaled according to the currency's
     * precision.
     *
     * @param money the {@code Money} instance to add
     * @return a new {@code Money} instance representing the sum of this and the
     *         specified {@code Money}
     */
    public Money add(Money money) {
        return new Money(setScale(amount.add(money.getAmount())));
    }

    /**
     * Subtracts the specified {@code Money} amount from this {@code Money} instance
     * and returns a new {@code Money} object
     * representing the result. The resulting amount is scaled according to the
     * currency's precision.
     *
     * @param money the {@code Money} instance to subtract from this amount
     * @return a new {@code Money} instance representing the difference
     */
    public Money subtract(Money money) {
        return new Money(setScale(amount.subtract(money.getAmount())));
    }

    /**
     * Returns a new {@code Money} instance whose value is the product of this
     * {@code Money}'s amount
     * and the specified multiplier.
     *
     * <p>
     * The multiplication is performed using {@link BigDecimal#multiply(BigDecimal)}
     * and the result
     * is scaled using the {@code setScale} method to ensure the correct number of
     * decimal places.
     *
     * @param multiplier the integer value to multiply this {@code Money} amount by
     * @return a new {@code Money} instance representing the multiplied value
     */
    public Money multiply(int multiplier) {
        return new Money(setScale(amount.multiply(new BigDecimal(multiplier))));
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The equality is based on the value of the {@code amount} field.
     * Two {@code Money} objects are considered equal if they are of the same class
     * and their {@code amount} values are equal.
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
        Money money = (Money) obj;
        return amount.equals(money.amount);
    }

    /**
     * Returns a hash code value for this {@code Money} object based on its
     * {@code amount} field.
     * This implementation uses {@link Objects#hashCode(Object)} to compute the hash
     * code.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    /**
     * Sets the scale of the given {@link BigDecimal} to 2 decimal places using
     * {@link RoundingMode#HALF_EVEN}.
     *
     * @param input the {@code BigDecimal} value to be scaled
     * @return a {@code BigDecimal} with scale set to 2 and rounded using HALF_EVEN
     *         mode
     */
    private BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }
}
