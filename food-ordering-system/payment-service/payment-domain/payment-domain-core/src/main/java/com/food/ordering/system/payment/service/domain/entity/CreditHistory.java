package com.food.ordering.system.payment.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.payment.service.domain.valueobject.CreditHistoryId;
import com.food.ordering.system.payment.service.domain.valueobject.TransactionType;

/**
 * Represents a credit history entry in the payment system.
 * This entity tracks individual credit transactions for customers,
 * recording the amount, transaction type, and associated customer.
 */
public class CreditHistory extends BaseEntity<CreditHistoryId> {
    /**
     * The unique identifier of the customer associated with this credit history
     * entry.
     */
    private final CustomerId customerId;

    /**
     * The monetary amount involved in this credit transaction.
     */
    private final Money amount;

    /**
     * The type of transaction (e.g., CREDIT, DEBIT) for this credit history entry.
     */
    private final TransactionType transactionType;

    /**
     * Private constructor used by the builder to create a new CreditHistory
     * instance.
     *
     * @param builder the builder containing the credit history data
     */
    private CreditHistory(Builder builder) {
        setId(builder.creditHistoryId);
        customerId = builder.customerId;
        amount = builder.amount;
        transactionType = builder.transactionType;
    }

    /**
     * Creates a new builder instance for constructing a CreditHistory.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the customer identifier associated with this credit history entry.
     *
     * @return the customer ID
     */
    public CustomerId getCustomerId() {
        return customerId;
    }

    /**
     * Gets the monetary amount involved in this credit transaction.
     *
     * @return the transaction amount
     */
    public Money getAmount() {
        return amount;
    }

    /**
     * Gets the type of transaction for this credit history entry.
     *
     * @return the transaction type
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Builder class for constructing CreditHistory instances using the builder
     * pattern.
     */
    public static final class Builder {
        /**
         * The unique identifier for the credit history entry.
         */
        private CreditHistoryId creditHistoryId;

        /**
         * The customer identifier for this credit history entry.
         */
        private CustomerId customerId;

        /**
         * The monetary amount for this credit transaction.
         */
        private Money amount;

        /**
         * The type of transaction for this credit history entry.
         */
        private TransactionType transactionType;

        /**
         * Private constructor to prevent direct instantiation.
         */
        private Builder() {
        }

        /**
         * Sets the credit history ID for the credit history entry being built.
         *
         * @param val the credit history ID
         * @return this builder instance for method chaining
         */
        public Builder creditHistoryId(CreditHistoryId val) {
            creditHistoryId = val;
            return this;
        }

        /**
         * Sets the customer ID for the credit history entry being built.
         *
         * @param val the customer ID
         * @return this builder instance for method chaining
         */
        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        /**
         * Sets the amount for the credit history entry being built.
         *
         * @param val the transaction amount
         * @return this builder instance for method chaining
         */
        public Builder amount(Money val) {
            amount = val;
            return this;
        }

        /**
         * Sets the transaction type for the credit history entry being built.
         *
         * @param val the transaction type
         * @return this builder instance for method chaining
         */
        public Builder transactionType(TransactionType val) {
            transactionType = val;
            return this;
        }

        /**
         * Builds and returns a new CreditHistory instance with the configured values.
         *
         * @return a new CreditHistory instance
         */
        public CreditHistory build() {
            return new CreditHistory(this);
        }
    }
}
