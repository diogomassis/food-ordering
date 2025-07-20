package com.food.ordering.system.payment.service.domain.entity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.PaymentStatus;
import com.food.ordering.system.payment.service.domain.valueobject.PaymentId;

/**
 * Represents a payment entity in the payment service domain.
 * This aggregate root manages payment information including order details,
 * customer information, price, status, and creation timestamp.
 */
public class Payment extends AggregateRoot<PaymentId> {
    /**
     * The unique identifier of the order associated with this payment.
     */
    private final OrderId orderId;

    /**
     * The unique identifier of the customer making the payment.
     */
    private final CustomerId customerId;

    /**
     * The total price amount for this payment.
     */
    private final Money price;

    /**
     * The current status of the payment (e.g., PENDING, COMPLETED, FAILED).
     */
    private PaymentStatus paymentStatus;

    /**
     * The timestamp when this payment was created, stored in UTC timezone.
     */
    private ZonedDateTime createdAt;

    /**
     * Initializes the payment by generating a unique payment ID and setting the
     * creation timestamp.
     * This method should be called when creating a new payment to ensure proper
     * initialization.
     */
    public void initializePayment() {
        setId(new PaymentId(UUID.randomUUID()));
        createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    /**
     * Validates the payment and adds any validation failure messages to the
     * provided list.
     * Currently validates that the price is greater than zero.
     *
     * @param failureMessages the list to add validation failure messages to
     */
    public void validatePayment(List<String> failureMessages) {
        if (price == null || !price.isGreaterThanZero()) {
            failureMessages.add("Total price must be greater than zero!");
        }
    }

    /**
     * Updates the payment status to the specified value.
     *
     * @param paymentStatus the new payment status to set
     */
    public void updateStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Private constructor used by the builder to create a new Payment instance.
     *
     * @param builder the builder containing the payment data
     */
    private Payment(Builder builder) {
        setId(builder.paymentId);
        orderId = builder.orderId;
        customerId = builder.customerId;
        price = builder.price;
        paymentStatus = builder.paymentStatus;
        createdAt = builder.createdAt;
    }

    /**
     * Creates a new builder instance for constructing a Payment.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the order identifier associated with this payment.
     *
     * @return the order ID
     */
    public OrderId getOrderId() {
        return orderId;
    }

    /**
     * Gets the customer identifier associated with this payment.
     *
     * @return the customer ID
     */
    public CustomerId getCustomerId() {
        return customerId;
    }

    /**
     * Gets the total price amount for this payment.
     *
     * @return the payment price
     */
    public Money getPrice() {
        return price;
    }

    /**
     * Gets the current status of this payment.
     *
     * @return the payment status
     */
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Gets the timestamp when this payment was created.
     *
     * @return the creation timestamp in UTC timezone
     */
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Builder class for constructing Payment instances using the builder pattern.
     */
    public static final class Builder {
        /**
         * The unique identifier for the payment.
         */
        private PaymentId paymentId;

        /**
         * The order identifier associated with this payment.
         */
        private OrderId orderId;

        /**
         * The customer identifier for this payment.
         */
        private CustomerId customerId;

        /**
         * The total price amount for this payment.
         */
        private Money price;

        /**
         * The status of the payment.
         */
        private PaymentStatus paymentStatus;

        /**
         * The timestamp when the payment was created.
         */
        private ZonedDateTime createdAt;

        /**
         * Private constructor to prevent direct instantiation.
         */
        private Builder() {
        }

        /**
         * Sets the payment ID for the payment being built.
         *
         * @param val the payment ID
         * @return this builder instance for method chaining
         */
        public Builder paymentId(PaymentId val) {
            paymentId = val;
            return this;
        }

        /**
         * Sets the order ID for the payment being built.
         *
         * @param val the order ID
         * @return this builder instance for method chaining
         */
        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        /**
         * Sets the customer ID for the payment being built.
         *
         * @param val the customer ID
         * @return this builder instance for method chaining
         */
        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        /**
         * Sets the price for the payment being built.
         *
         * @param val the payment price
         * @return this builder instance for method chaining
         */
        public Builder price(Money val) {
            price = val;
            return this;
        }

        /**
         * Sets the payment status for the payment being built.
         *
         * @param val the payment status
         * @return this builder instance for method chaining
         */
        public Builder paymentStatus(PaymentStatus val) {
            paymentStatus = val;
            return this;
        }

        /**
         * Sets the creation timestamp for the payment being built.
         *
         * @param val the creation timestamp
         * @return this builder instance for method chaining
         */
        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        /**
         * Builds and returns a new Payment instance with the configured values.
         *
         * @return a new Payment instance
         */
        public Payment build() {
            return new Payment(this);
        }
    }
}
