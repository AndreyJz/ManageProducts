package com.store.back.application.service;

import com.store.back.domain.entities.PurchaseProduct;
import com.store.back.domain.entities.PurchaseProductPk;

import java.util.List;
import java.util.Optional;

public interface IPurchaseProductService {
    List<PurchaseProduct> listPurchaseProduct();
    Optional<PurchaseProduct> findPurchaseProductById(PurchaseProductPk purchaseProductPk);
    PurchaseProduct save(PurchaseProduct purchaseProduct);
    Optional<PurchaseProduct> updatePurchaseProduct(PurchaseProductPk purchaseProductPk, PurchaseProduct purchaseProduct);
    Optional<PurchaseProduct> deletePurchaseProduct(PurchaseProductPk purchaseProductPk);

}
