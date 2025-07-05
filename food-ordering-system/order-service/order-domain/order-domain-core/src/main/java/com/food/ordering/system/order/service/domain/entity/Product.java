package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;

/**
 * Represents a product entity in the order domain.
 * Contains product details such as name and price.
 * Extends {@link BaseEntity} with a {@link ProductId} as its identifier.
 */
public class Product extends BaseEntity<ProductId> {
    /**
     * The name of the product.
     */
    private String name;
    /**
     * The price of the product represented as a {@link Money} object.
     * This field holds the monetary value associated with the product.
     */
    private Money price;

    /**
     * Constructs a new {@code Product} instance with the specified product ID,
     * name, and price.
     *
     * @param productId the unique identifier for the product
     * @param name      the name of the product
     * @param price     the price of the product represented as a {@link Money}
     *                  object
     */
    public Product(ProductId productId, String name, Money price) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return the {@link Money} object representing the product's price
     */
    public Money getPrice() {
        return price;
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}
