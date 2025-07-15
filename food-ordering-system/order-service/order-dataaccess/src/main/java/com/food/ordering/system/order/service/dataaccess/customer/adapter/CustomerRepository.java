package com.food.ordering.system.order.service.dataaccess.customer.adapter;

import java.util.Optional;
import java.util.UUID;

import com.food.ordering.system.order.service.dataaccess.customer.mapper.CustomerDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.customer.repository.ICustomerJpaRepository;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.ports.output.repository.ICustomerRepository;

public class CustomerRepository implements ICustomerRepository {
    private final ICustomerJpaRepository customerJpaRepository;
    private final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepository(ICustomerJpaRepository customerJpaRepository,
            CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId).map(customerDataAccessMapper::customerEntityToCustomer);
    }
}
