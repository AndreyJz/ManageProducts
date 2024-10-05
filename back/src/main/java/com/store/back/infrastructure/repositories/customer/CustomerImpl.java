package com.store.back.infrastructure.repositories.customer;

import com.store.back.application.service.ICustomerService;
import com.store.back.domain.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Customer> listCustomer() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Customer> findCustomerById(String id) {
        return customerRepository.findById(id);
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        System.out.println(customer.getName());
        System.out.println("hola");
        System.out.println(customer.getLastName());
        return customerRepository.save(customer);
    }

    @Transactional
    @Override
    public Optional<Customer> updateCustomer(String id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customerToUpdate = customerRepository.save(customer);
            customerToUpdate.setName(customer.getName());
            customerToUpdate.setLastName(customer.getLastName());
            customerToUpdate.setPhone(customer.getPhone());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setAddress(customer.getAddress());
            return Optional.of(customerRepository.save(customerToUpdate));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Customer> deleteCustomer(String id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            customerRepository.delete(customerOptional.get());
        }
        return Optional.empty();
    }
}
