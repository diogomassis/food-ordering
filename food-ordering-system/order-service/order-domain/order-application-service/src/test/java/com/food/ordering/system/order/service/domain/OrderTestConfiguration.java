package com.food.ordering.system.order.service.domain;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.ports.output.repository.ICustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.IOrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.IRestaurantRepository;

/**
 * Test configuration for the Order domain service.
 * Provides mocked beans for dependencies required in tests.
 */
@SpringBootApplication(scanBasePackages = "com.food.ordering.system")
public class OrderTestConfiguration {

    /**
     * Provides a mock for OrderCreatedPaymentRequestMessagePublisher.
     *
     * @return a mock instance of OrderCreatedPaymentRequestMessagePublisher
     */
    @Bean
    public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
    }

    /**
     * Provides a mock for OrderCancelledPaymentRequestMessagePublisher.
     *
     * @return a mock instance of OrderCancelledPaymentRequestMessagePublisher
     */
    @Bean
    public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
    }

    /**
     * Provides a mock for OrderPaidRestaurantRequestMessagePublisher.
     *
     * @return a mock instance of OrderPaidRestaurantRequestMessagePublisher
     */
    @Bean
    public OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher() {
        return Mockito.mock(OrderPaidRestaurantRequestMessagePublisher.class);
    }

    /**
     * Provides a mock for IOrderRepository.
     *
     * @return a mock instance of IOrderRepository
     */
    @Bean
    public IOrderRepository orderRepository() {
        return Mockito.mock(IOrderRepository.class);
    }

    /**
     * Provides a mock for ICustomerRepository.
     *
     * @return a mock instance of ICustomerRepository
     */
    @Bean
    public ICustomerRepository customerRepository() {
        return Mockito.mock(ICustomerRepository.class);
    }

    /**
     * Provides a mock for IRestaurantRepository.
     *
     * @return a mock instance of IRestaurantRepository
     */
    @Bean
    public IRestaurantRepository restaurantRepository() {
        return Mockito.mock(IRestaurantRepository.class);
    }

    /**
     * Provides an instance of OrderDomainService.
     *
     * @return a new instance of OrderDomainService
     */
    @Bean
    public IOrderDomainService orderDomainService() {
        return new OrderDomainService();
    }
}
