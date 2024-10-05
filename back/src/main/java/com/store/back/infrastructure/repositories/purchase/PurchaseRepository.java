package com.store.back.infrastructure.repositories.purchase;

import com.store.back.domain.entities.Category;
import com.store.back.domain.entities.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
