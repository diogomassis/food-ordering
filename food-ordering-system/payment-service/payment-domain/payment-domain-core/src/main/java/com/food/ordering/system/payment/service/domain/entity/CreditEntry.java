package com.food.ordering.system.payment.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.payment.service.domain.valueobject.CreditEntryId;

/**
 * Represents a credit entry for a customer in the payment system.
 * This entity tracks the total credit amount available for a specific customer.
 */
public class CreditEntry extends BaseEntity<CreditEntryId> {
    /**
     * The unique identifier of the customer associated with this credit entry.
     */
    private final CustomerId customerId;

    /**
     * The total credit amount available for the customer.
     */
    private Money totalCreditAmount;

    /**
     * Adds the specified amount to the total credit amount.
     *
     * @param amount the money amount to add to the credit
     */
    public void addCreditAmount(Money amount) {
        totalCreditAmount = totalCreditAmount.add(amount);
    }

    /**
     * Subtracts the specified amount from the total credit amount.
     *
     * @param amount the money amount to subtract from the credit
     */
    public void subtractCreditAmount(Money amount) {
        totalCreditAmount = totalCreditAmount.subtract(amount);
    }

    /**
     * Private constructor used by the builder to create a new CreditEntry instance.
     *
     * @param builder the builder containing the credit entry data
     */
    private CreditEntry(Builder builder) {
        setId(builder.creditEntryId);
        customerId = builder.customerId;
        totalCreditAmount = builder.totalCreditAmount;
    }

    /**
     * Creates a new builder instance for constructing a CreditEntry.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the customer identifier associated with this credit entry.
     *
     * @return the customer ID
     */
    public CustomerId getCustomerId() {
        return customerId;
    }

    /**
     * Gets the total credit amount available for the customer.
     *
     * @return the total credit amount
     */
    public Money getTotalCreditAmount() {
        return totalCreditAmount;
    }

    /**
     * Builder class for constructing CreditEntry instances using the builder
     * pattern.
     */
    public static final class Builder {
        /**
         * The unique identifier for the credit entry.
         */
        private CreditEntryId creditEntryId;

        /**
         * The customer identifier for this credit entry.
         */
        private CustomerId customerId;

        /**
         * The total credit amount for the customer.
         */
        private Money totalCreditAmount;

        /**
         * Private constructor to prevent direct instantiation.
         */
        private Builder() {
        }

        /**
         * Sets the credit entry ID for the credit entry being built.
         *
         * @param val the credit entry ID
         * @return this builder instance for method chaining
         */
        public Builder creditEntryId(CreditEntryId val) {
            creditEntryId = val;
            return this;
        }

        /**
         * Sets the customer ID for the credit entry being built.
         *
         * @param val the customer ID
         * @return this builder instance for method chaining
         */
        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        /**
         * Sets the total credit amount for the credit entry being built.
         *
         * @param val the total credit amount
         * @return this builder instance for method chaining
         */
        public Builder totalCreditAmount(Money val) {
            totalCreditAmount = val;
            return this;
        }

        /**
         * Builds and returns a new CreditEntry instance with the configured values.
         *
         * @return a new CreditEntry instance
         */
        public CreditEntry build() {
            return new CreditEntry(this);
        }
    }
}
