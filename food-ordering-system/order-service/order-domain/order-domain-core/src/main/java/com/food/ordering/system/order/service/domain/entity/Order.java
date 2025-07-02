package com.food.ordering.system.order.service.domain.entity;

import java.util.List;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

/**
 * Represents an Order aggregate root in the food ordering system domain.
 * <p>
 * An Order contains information about the customer, restaurant, delivery
 * address,
 * total price, order items, tracking, status, and any failure messages.
 * </p>
 *
 * <p>
 * Use the {@link Builder} to create instances of this class.
 * </p>
 */
public class Order extends AggregateRoot<OrderId> {
    /**
     * The unique identifier of the customer who placed the order.
     */
    private final CustomerId customerId;
    /**
     * The unique identifier of the restaurant associated with this order.
     */
    private final RestaurantId restaurantId;
    /**
     * The delivery address associated with the order.
     * This is represented by a {@link StreetAddress} object and contains
     * the details of where the order should be delivered.
     */
    private final StreetAddress deliveryAddress;
    /**
     * The total price of the order.
     * This value represents the aggregate cost of all items in the order,
     * including any applicable taxes, discounts, or fees.
     */
    private final Money price;
    /**
     * The list of items included in the order.
     * Each {@link OrderItem} represents a product and its quantity within the
     * order.
     * This list is immutable and contains all the items that are part of this
     * order.
     */
    private final List<OrderItem> items;

    /**
     * Unique identifier used to track the order throughout its lifecycle.
     */
    private TrackingId trackingId;
    /**
     * Represents the current status of the order.
     * This field tracks the lifecycle state of the order, such as CREATED, PAID,
     * APPROVED, or CANCELLED.
     */
    private OrderStatus orderStatus;
    /**
     * A list of failure messages associated with the order.
     * This list is used to store error or failure messages that occur during order
     * processing,
     * such as validation errors or business rule violations.
     */
    private List<String> failureMessages;

    /**
     * Constructs an Order instance using the provided Builder.
     * <p>
     * This private constructor initializes the Order entity with values from the
     * Builder,
     * including identifiers, delivery address, price, items, tracking information,
     * order status,
     * and any failure messages.
     *
     * @param builder the Builder instance containing all necessary Order attributes
     */
    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    /**
     * Creates a new instance of the {@link Builder} for constructing {@link Order}
     * objects.
     *
     * @return a new {@link Builder} instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Retrieves the unique identifier of the customer who placed the order.
     *
     * @return the {@link CustomerId} associated with this order
     */
    public CustomerId getCustomerId() {
        return customerId;
    }

    /**
     * Retrieves the unique identifier of the restaurant associated with this order.
     *
     * @return the {@link RestaurantId} representing the restaurant for this order
     */
    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    /**
     * Retrieves the delivery address associated with this order.
     *
     * @return the {@link StreetAddress} where the order should be delivered
     */
    public StreetAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * Retrieves the total price of the order.
     *
     * @return the {@link Money} object representing the price of the order
     */
    public Money getPrice() {
        return price;
    }

    /**
     * Retrieves the list of items associated with this order.
     *
     * @return a list of {@link OrderItem} objects representing the items in the
     *         order
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * Retrieves the tracking ID associated with this order.
     *
     * @return the {@link TrackingId} of the order, used to track the order status
     *         and progress.
     */
    public TrackingId getTrackingId() {
        return trackingId;
    }

    /**
     * Retrieves the current status of the order.
     *
     * @return the {@link OrderStatus} representing the current state of the order.
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Retrieves the list of failure messages associated with the order.
     *
     * @return a list of strings containing failure messages, or an empty list if
     *         there are none.
     */
    public List<String> getFailureMessages() {
        return failureMessages;
    }

    /**
     * Builder class for constructing instances of {@link Order}.
     * <p>
     * This builder provides a fluent API to set the various properties required to
     * create an {@link Order} object,
     * such as order identifiers, customer and restaurant information, delivery
     * address, pricing, items, tracking,
     * status, and failure messages.
     * </p>
     * <p>
     * Usage example:
     * 
     * <pre>
     *     Order order = Order.Builder()
     *         .orderId(new OrderId(...))
     *         .customerId(new CustomerId(...))
     *         .restaurantId(new RestaurantId(...))
     *         .deliveryAddress(new StreetAddress(...))
     *         .price(new Money(...))
     *         .items(orderItems)
     *         .trackingId(new TrackingId(...))
     *         .orderStatus(OrderStatus.CREATED)
     *         .failureMessages(Collections.emptyList())
     *         .build();
     * </pre>
     * </p>
     * <p>
     * All fields are optional until set, but required fields must be provided
     * before calling {@link #build()}.
     * </p>
     */
    public static final class Builder {
        /**
         * Unique identifier for the Order entity.
         */
        private OrderId orderId;
        /**
         * Unique identifier for the customer who placed the order.
         */
        private CustomerId customerId;
        /**
         * The unique identifier of the restaurant associated with this order.
         */
        private RestaurantId restaurantId;
        /**
         * The delivery address associated with the order.
         * This is represented by a {@link StreetAddress} object and contains
         * the details of where the order should be delivered.
         */
        private StreetAddress deliveryAddress;
        /**
         * The total price of the order.
         * This value represents the aggregate cost of all items in the order,
         * including any applicable taxes, discounts, or fees.
         */
        private Money price;
        /**
         * The list of items included in the order.
         * Each {@link OrderItem} represents a product and its quantity within the
         * order.
         * This list is immutable and contains all the items that are part of this
         * order.
         */
        private List<OrderItem> items;

        /**
         * Unique identifier used to track the order throughout its lifecycle.
         */
        private TrackingId trackingId;
        /**
         * Represents the current status of the order.
         * This field tracks the lifecycle state of the order, such as CREATED, PAID,
         * APPROVED, or CANCELLED.
         */
        private OrderStatus orderStatus;
        /**
         * A list of failure messages associated with the order.
         * This list is used to store error or failure messages that occur during order
         * processing,
         * such as validation errors or business rule violations.
         */
        private List<String> failureMessages;

        /**
         * Private constructor for the {@code Builder} class.
         * Prevents direct instantiation from outside the {@code Builder},
         * enforcing the use of the enclosing class's builder pattern.
         */
        private Builder() {
        }

        /**
         * Sets the unique identifier for the order.
         *
         * @param orderId the {@link OrderId} representing the unique ID of the order
         * @return the current {@code Builder} instance for method chaining
         */
        public Builder orderId(OrderId orderId) {
            this.orderId = orderId;
            return this;
        }

        /**
         * Sets the customer ID for the order.
         *
         * @param customerId the unique identifier of the customer placing the order
         * @return the current Builder instance for method chaining
         */
        public Builder customerId(CustomerId customerId) {
            this.customerId = customerId;
            return this;
        }

        /**
         * Sets the restaurant identifier for the order.
         *
         * @param restaurantId the unique identifier of the restaurant associated with
         *                     the order
         * @return the current Builder instance for method chaining
         */
        public Builder restaurantId(RestaurantId restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        /**
         * Sets the delivery address for the order.
         *
         * @param deliveryAddress the {@link StreetAddress} where the order should be
         *                        delivered
         * @return the current {@code Builder} instance for method chaining
         */
        public Builder deliveryAddress(StreetAddress deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        /**
         * Sets the price for the order.
         *
         * @param price the {@link Money} object representing the total price of the
         *              order
         * @return the current {@link Builder} instance for method chaining
         */
        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        /**
         * Sets the list of {@link OrderItem} objects for the order.
         *
         * @param items the list of items to be included in the order
         * @return the current Builder instance for method chaining
         */
        public Builder items(List<OrderItem> items) {
            this.items = items;
            return this;
        }

        /**
         * Sets the tracking ID for the order.
         *
         * @param trackingId the {@link TrackingId} to associate with the order
         * @return the current {@code Builder} instance for method chaining
         */
        public Builder trackingId(TrackingId trackingId) {
            this.trackingId = trackingId;
            return this;
        }

        /**
         * Sets the status of the order.
         *
         * @param orderStatus the {@link OrderStatus} to set for this order
         * @return the current {@code Builder} instance for method chaining
         */
        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        /**
         * Sets the list of failure messages associated with the order.
         *
         * @param failureMessages a list of strings representing failure messages
         * @return the current Builder instance for method chaining
         */
        public Builder failureMessages(List<String> failureMessages) {
            this.failureMessages = failureMessages;
            return this;
        }

        /**
         * Builds and returns a new {@link Order} instance using the current state of
         * the builder.
         *
         * @return a new {@code Order} object constructed from the builder's properties
         */
        public Order build() {
            return new Order(this);
        }
    }
}
