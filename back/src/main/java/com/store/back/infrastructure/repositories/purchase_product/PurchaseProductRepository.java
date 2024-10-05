package com.store.back.infrastructure.repositories.purchase_product;

import com.store.back.domain.entities.Category;
import com.store.back.domain.entities.PurchaseProduct;
import com.store.back.domain.entities.PurchaseProductPk;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseProductRepository extends CrudRepository<PurchaseProduct, PurchaseProductPk> {
}
