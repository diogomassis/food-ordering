package com.food.ordering.system.order.service.domain.ports.output.repository;

import java.util.Optional;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.order.service.domain.entity.Customer;

public interface ICustomerRepository {
    Optional<Customer> findCustomer(CustomerId customerId);
}
