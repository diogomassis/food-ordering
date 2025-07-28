package com.food.ordering.system.order.service.domain;

import java.util.List;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

/**
 * Domain service interface for handling order-related business logic in the
 * food ordering system.
 * <p>
 * This interface defines the contract for core order operations, including
 * order validation, payment, approval, and cancellation. Implementations of
 * this
 * interface encapsulate the domain logic for managing the lifecycle of an
 * {@link Order} aggregate, coordinating with related entities such as
 * {@link Restaurant} and handling domain events like {@link OrderCreatedEvent},
 * {@link OrderPaidEvent}, and {@link OrderCancelledEvent}.
 * </p>
 *
 * <p>
 * Typical usage involves invoking these methods from application services to
 * perform domain operations in a transactional and consistent manner.
 * </p>
 */
public interface IOrderDomainService {
        /**
         * Validates the provided order and initiates its creation process.
         * <p>
         * This method checks the order's validity, associates it with the specified
         * restaurant,
         * and triggers the creation event if successful.
         * </p>
         *
         * @param order                the order to validate and initiate
         * @param restaurant           the restaurant associated with the order
         * @param domainEventPublisher the publisher for the order created event
         * @return an {@link OrderCreatedEvent} representing the result of the operation
         */
        OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant,
                        IDomainEventPublisher<OrderCreatedEvent> domainEventPublisher);

        /**
         * Processes payment for the given order.
         * <p>
         * Changes the order's state to PAID and triggers the corresponding domain
         * event.
         * </p>
         *
         * @param order                the order to pay
         * @param domainEventPublisher the publisher for the order paid event
         * @return an {@link OrderPaidEvent} representing the result of the payment
         *         operation
         */
        OrderPaidEvent payOrder(Order order, IDomainEventPublisher<OrderPaidEvent> domainEventPublisher);

        /**
         * Approves the specified order.
         * <p>
         * Changes the order's state to APPROVED. No event is returned.
         * </p>
         *
         * @param order the order to approve
         */
        void approveOrder(Order order);

        /**
         * Initiates cancellation of the order payment process.
         * <p>
         * Changes the order's state to CANCELLING and triggers the corresponding domain
         * event.
         * </p>
         *
         * @param order                the order to cancel payment for
         * @param failureMessages      a list of failure messages explaining the
         *                             cancellation
         * @param domainEventPublisher the publisher for the order cancelled event
         * @return an {@link OrderCancelledEvent} representing the result of the
         *         cancellation
         */
        OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages,
                        IDomainEventPublisher<OrderCancelledEvent> domainEventPublisher);

        /**
         * Cancels the specified order.
         * <p>
         * Changes the order's state to CANCELLED. No event is returned.
         * </p>
         *
         * @param order the order to cancel
         */
        void cancelOrder(Order order);
}
