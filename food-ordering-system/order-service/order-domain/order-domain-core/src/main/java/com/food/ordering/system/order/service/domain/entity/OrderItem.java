package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

/**
 * Represents an item within an order, containing product details, quantity,
 * price, and subtotal.
 * <p>
 * Each {@code OrderItem} is associated with a specific {@link Product} and
 * maintains information
 * about the quantity ordered, the price per unit, and the subtotal for this
 * item.
 * </p>
 *
 * <p>
 * Instances of this class are created using the {@link Builder} pattern.
 * </p>
 */
public class OrderItem extends BaseEntity<OrderItemId> {
    /**
     * The unique identifier of the order to which this item belongs.
     */
    private OrderId orderId;
    /**
     * The product associated with this order item.
     * Represents the specific product being ordered.
     */
    private final Product product;
    /**
     * The quantity of the specific item in the order.
     * Represents how many units of the product are included in this order item.
     */
    private final int quantity;
    /**
     * The price of a single unit of the order item.
     * This value represents the cost for one item, excluding quantity.
     */
    private final Money price;
    /**
     * The subtotal amount for this order item, represented as a {@link Money}
     * object.
     * This value typically reflects the total price for the quantity of this item
     * in the order,
     * before any additional charges such as taxes or discounts are applied.
     */
    private final Money subTotal;

    /**
     * Constructs an {@code OrderItem} instance using the provided {@link Builder}.
     * Initializes the order item with the specified product, quantity, price, and
     * subtotal.
     *
     * @param builder the builder containing the order item properties
     */
    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }
    public void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        setId(orderItemId);
    }

    public boolean isPriceValid() {
        return price.isGreaterThanZero() && price.equals(product.getPrice())
                && price.multiply(quantity).equals(subTotal);
    }

    /**
     * Creates a new instance of the {@link Builder} for constructing
     * {@link OrderItem} objects.
     *
     * @return a new {@link Builder} instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Retrieves the unique identifier of the order associated with this order item.
     *
     * @return the {@link OrderId} of the order to which this item belongs
     */
    public OrderId getOrderId() {
        return orderId;
    }

    /**
     * Retrieves the product associated with this order item.
     *
     * @return the {@link Product} linked to this order item
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Returns the quantity of the order item.
     *
     * @return the quantity of this order item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Retrieves the price of this order item.
     *
     * @return the {@link Money} object representing the price of the order item
     */
    public Money getPrice() {
        return price;
    }

    /**
     * Retrieves the subtotal amount for this order item.
     *
     * @return the {@link Money} representing the subtotal of the order item
     */
    public Money getSubTotal() {
        return subTotal;
    }

    /**
     * Builder class for constructing instances of {@link OrderItem}.
     * <p>
     * This builder provides a fluent API to set the properties of an
     * {@link OrderItem}
     * such as orderItemId, product, quantity, price, and subTotal.
     * </p>
     * <p>
     * Usage example:
     * 
     * <pre>
     * OrderItem orderItem = OrderItem.builder()
     *         .orderItemId(orderItemId)
     *         .product(product)
     *         .quantity(quantity)
     *         .price(price)
     *         .subTotal(subTotal)
     *         .build();
     * </pre>
     * </p>
     */
    public static final class Builder {
        /**
         * Unique identifier for the order item.
         */
        private OrderItemId orderItemId;
        /**
         * The product associated with this order item.
         * Represents the specific product being ordered.
         */
        private Product product;
        /**
         * The quantity of the product in this order item.
         * Represents how many units of the associated product are included in the
         * order.
         */
        private int quantity;
        /**
         * The price of a single unit of the order item.
         * This value represents the cost for one item, excluding quantity.
         */
        private Money price;
        /**
         * The subtotal amount for this order item, represented as a {@link Money}
         * object.
         * This value typically reflects the total price for the quantity of this item
         * in the order,
         * before any additional charges such as taxes or discounts are applied.
         */
        private Money subTotal;

        /**
         * Private constructor for the {@code Builder} class.
         * <p>
         * This constructor prevents direct instantiation of the {@code Builder} from
         * outside the enclosing class,
         * enforcing the use of a factory method or a builder pattern for object
         * creation.
         */
        private Builder() {
        }

        /**
         * Sets the unique identifier for the order item.
         *
         * @param orderItemId the {@link OrderItemId} representing the unique ID of the
         *                    order item
         * @return the current {@link Builder} instance for method chaining
         */
        public Builder orderItemId(OrderItemId orderItemId) {
            this.orderItemId = orderItemId;
            return this;
        }

        /**
         * Sets the {@link Product} associated with this order item.
         *
         * @param product the product to associate with this order item
         * @return the current {@code Builder} instance for method chaining
         */
        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        /**
         * Sets the quantity of the order item.
         *
         * @param quantity the number of units for this order item
         * @return the Builder instance for method chaining
         */
        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * Sets the price for the order item.
         *
         * @param price the {@link Money} value representing the price of the order item
         * @return the current {@code Builder} instance for method chaining
         */
        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        /**
         * Sets the subtotal amount for the order item.
         *
         * @param subTotal the subtotal value as a {@link Money} object
         * @return the current {@code Builder} instance for method chaining
         */
        public Builder subTotal(Money subTotal) {
            this.subTotal = subTotal;
            return this;
        }

        /**
         * Builds and returns a new {@link OrderItem} instance using the current state
         * of the builder.
         *
         * @return a new {@code OrderItem} object constructed from the builder's fields
         */
        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
