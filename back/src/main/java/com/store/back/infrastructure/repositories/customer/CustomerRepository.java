package com.store.back.infrastructure.repositories.customer;

import com.store.back.domain.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {
}
