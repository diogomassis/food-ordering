package com.food.ordering.system.order.service.dataaccess.order.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderAddressEntity;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderEntity;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderItemEntity;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

/**
 * Data access mapper for converting between Order domain objects and Order data
 * access entities.
 * This mapper handles the transformation of Order objects to and from their
 * corresponding database entities,
 * providing a clean separation between the domain layer and data access layer.
 */
@Component
public class OrderDataAccessMapper {

        /**
         * Converts an Order domain object to an OrderEntity for database persistence.
         * Sets up bidirectional relationships between the order entity and its
         * associated entities.
         *
         * @param order the Order domain object to convert
         * @return OrderEntity ready for database persistence with all relationships
         *         properly set
         */
        public OrderEntity orderToOrderEntity(Order order) {
                OrderEntity orderEntity = OrderEntity.builder()
                                .id(order.getId().getValue())
                                .customerId(order.getCustomerId().getValue())
                                .restaurantId(order.getRestaurantId().getValue())
                                .trackingId(order.getTrackingId().getValue())
                                .address(deliveryAddressToAddressEntity(order.getDeliveryAddress()))
                                .price(order.getPrice().getAmount())
                                .items(orderItemsToOrderItemEntities(order.getItems()))
                                .orderStatus(order.getOrderStatus())
                                .failureMessages(order.getFailureMessages() != null
                                                ? String.join(Order.FAILURE_MESSAGE_DELIMITER,
                                                                order.getFailureMessages())
                                                : "")
                                .build();
                orderEntity.getAddress().setOrder(orderEntity);
                orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));
                return orderEntity;
        }

        /**
         * Converts an OrderEntity from the database to an Order domain object.
         * Reconstructs the domain object with all its value objects and collections.
         *
         * @param orderEntity the OrderEntity from the database
         * @return Order domain object with all properties and relationships properly
         *         set
         */
        public Order orderEntityToOrder(OrderEntity orderEntity) {
                return Order.builder()
                                .orderId(new OrderId(orderEntity.getId()))
                                .customerId(new CustomerId(orderEntity.getCustomerId()))
                                .restaurantId(new RestaurantId(orderEntity.getRestaurantId()))
                                .deliveryAddress(addressEntityToDeliveryAddress(orderEntity.getAddress()))
                                .price(new Money(orderEntity.getPrice()))
                                .items(orderItemEntitiesToOrderItems(orderEntity.getItems()))
                                .trackingId(new TrackingId(orderEntity.getTrackingId()))
                                .orderStatus(orderEntity.getOrderStatus())
                                .failureMessages(orderEntity.getFailureMessages().isEmpty() ? new ArrayList<>()
                                                : new ArrayList<>(Arrays.asList(orderEntity.getFailureMessages()
                                                                .split(Order.FAILURE_MESSAGE_DELIMITER))))
                                .build();
        }

        /**
         * Converts a list of OrderItemEntity objects to a list of OrderItem domain
         * objects.
         * Each order item entity is transformed to include proper value objects and
         * calculations.
         *
         * @param items the list of OrderItemEntity objects from the database
         * @return list of OrderItem domain objects
         */
        private List<OrderItem> orderItemEntitiesToOrderItems(List<OrderItemEntity> items) {
                return items.stream()
                                .map(orderItemEntity -> OrderItem.builder()
                                                .orderItemId(new OrderItemId(orderItemEntity.getId()))
                                                .product(new Product(new ProductId(orderItemEntity.getProductId())))
                                                .price(new Money(orderItemEntity.getPrice()))
                                                .quantity(orderItemEntity.getQuantity())
                                                .subTotal(new Money(orderItemEntity.getSubTotal()))
                                                .build())
                                .collect(Collectors.toList());
        }

        /**
         * Converts an OrderAddressEntity to a StreetAddress value object.
         * Transforms the database address entity to the domain representation.
         *
         * @param address the OrderAddressEntity from the database
         * @return StreetAddress value object containing the address information
         */
        private StreetAddress addressEntityToDeliveryAddress(OrderAddressEntity address) {
                return new StreetAddress(address.getId(),
                                address.getStreet(),
                                address.getPostalCode(),
                                address.getCity());
        }

        /**
         * Converts a list of OrderItem domain objects to a list of OrderItemEntity
         * objects.
         * Each domain order item is transformed to its corresponding database entity
         * representation.
         *
         * @param items the list of OrderItem domain objects
         * @return list of OrderItemEntity objects ready for database persistence
         */
        private List<OrderItemEntity> orderItemsToOrderItemEntities(List<OrderItem> items) {
                return items.stream()
                                .map(orderItem -> OrderItemEntity.builder()
                                                .id(orderItem.getId().getValue())
                                                .productId(orderItem.getProduct().getId().getValue())
                                                .price(orderItem.getPrice().getAmount())
                                                .quantity(orderItem.getQuantity())
                                                .subTotal(orderItem.getSubTotal().getAmount())
                                                .build())
                                .collect(Collectors.toList());
        }

        /**
         * Converts a StreetAddress value object to an OrderAddressEntity.
         * Transforms the domain address representation to its corresponding database
         * entity.
         *
         * @param deliveryAddress the StreetAddress value object from the domain
         * @return OrderAddressEntity ready for database persistence
         */
        private OrderAddressEntity deliveryAddressToAddressEntity(StreetAddress deliveryAddress) {
                return OrderAddressEntity.builder()
                                .id(deliveryAddress.getId())
                                .street(deliveryAddress.getStreet())
                                .postalCode(deliveryAddress.getPostalCode())
                                .city(deliveryAddress.getCity())
                                .build();
        }
}
