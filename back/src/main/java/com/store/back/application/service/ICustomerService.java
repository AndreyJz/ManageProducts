package com.store.back.application.service;

import com.store.back.domain.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> listCustomer();
    Optional<Customer> findCustomerById(String id);
    Customer save(Customer customer);
    Optional<Customer> updateCustomer(String  id, Customer customer);
    Optional<Customer> deleteCustomer(String id);

}
