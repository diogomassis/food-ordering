package com.food.ordering.system.order.service.domain.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;

/**
 * Mapper class for converting order-related data between different layers.
 * Handles transformations between domain entities and DTOs for the order
 * service.
 */
@Component
public class OrderDataMapper {

        /**
         * Maps a {@link CreateOrderCommand} to a {@link Restaurant} domain entity.
         *
         * @param createOrderCommand the command containing restaurant and order item
         *                           information
         * @return a {@link Restaurant} entity populated with the provided data
         */
        public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
                return Restaurant.builder()
                                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                                .products(createOrderCommand.getItems().stream()
                                                .map(orderItem -> new Product(new ProductId(orderItem.getProductId())))
                                                .collect(Collectors.toList()))
                                .build();
        }

        /**
         * Maps a {@link CreateOrderCommand} to an {@link Order} domain entity.
         *
         * @param createOrderCommand the command containing order details
         * @return an {@link Order} entity populated with the provided data
         */
        public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
                return Order.builder()
                                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                                .price(new Money(createOrderCommand.getPrice()))
                                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                                .build();
        }

        /**
         * Maps an {@link Order} domain entity to a {@link CreateOrderResponse} DTO.
         *
         * @param order the {@link Order} entity to map
         * @return a {@link CreateOrderResponse} containing the order's tracking ID and
         *         status
         */
        public CreateOrderResponse orderToCreateOrderResponse(Order order) {
                return CreateOrderResponse.builder()
                                .ordeTrackingId(order.getTrackingId().getValue())
                                .orderStatus(order.getOrderStatus())
                                .build();
        }

        /**
         * Converts an {@link OrderAddress} DTO to a {@link StreetAddress} value object.
         *
         * @param orderAddress the address DTO to convert
         * @return a {@link StreetAddress} value object with a generated UUID
         */
        private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
                return new StreetAddress(UUID.randomUUID(), orderAddress.getStreet(), orderAddress.getPostalCode(),
                                orderAddress.getCity());
        }

        /**
         * Converts a list of
         * {@link com.food.ordering.system.order.service.domain.dto.create.OrderItem}
         * DTOs
         * to a list of {@link OrderItem} domain entities.
         *
         * @param orderItems the list of order item DTOs to convert
         * @return a list of {@link OrderItem} entities
         */
        private List<OrderItem> orderItemsToOrderItemEntities(
                        List<com.food.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {
                return orderItems.stream()
                                .map(orderItem -> OrderItem.builder()
                                                .product(new Product(new ProductId(orderItem.getProductId())))
                                                .price(new Money(orderItem.getPrice()))
                                                .quantity(orderItem.getQuantity())
                                                .subTotal(new Money(orderItem.getSubTotal()))
                                                .build())
                                .collect(Collectors.toList());
        }

}
