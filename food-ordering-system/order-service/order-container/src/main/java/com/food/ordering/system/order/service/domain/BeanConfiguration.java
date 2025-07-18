package com.food.ordering.system.order.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining beans in the order service domain.
 */
@Configuration
public class BeanConfiguration {

    /**
     * Creates and returns an instance of {@link IOrderDomainService}.
     *
     * @return a new instance of {@link OrderDomainService}
     */
    @Bean
    public IOrderDomainService orderDomainService() {
        return new OrderDomainService();
    }
}
