package com.food.ordering.system.payment.service.domain.valueobject;

/**
 * Enumeration representing the different types of financial transactions
 * that can be performed in the payment domain.
 */
public enum TransactionType {
    /**
     * Represents a debit transaction where money is withdrawn or charged
     * from an account or credit balance.
     */
    DEBIT,

    /**
     * Represents a credit transaction where money is added or credited
     * to an account or credit balance.
     */
    CREDIT
}
