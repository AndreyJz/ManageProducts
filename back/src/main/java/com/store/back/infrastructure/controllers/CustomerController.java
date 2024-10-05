package com.store.back.infrastructure.controllers;

import com.store.back.application.service.ICustomerService;
import com.store.back.domain.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping
    public List<Customer> getAllCategories() {
        return customerService.listCustomer();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable String id) {
        Optional<Customer> customerOptional = customerService.findCustomerById(id);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(customerOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findCustomerById(id);
        if (customerOptional.isPresent()) {
            customer.setId(id);
            return ResponseEntity.ok(customerService.save(customer));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Customer> customerOptional = customerService.findCustomerById(id);
        if (customerOptional.isPresent()) {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok(customerOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
