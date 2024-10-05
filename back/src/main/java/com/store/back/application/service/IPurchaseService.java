package com.store.back.application.service;

import com.store.back.domain.entities.Purchase;

import java.util.List;
import java.util.Optional;

public interface IPurchaseService {
    List<Purchase> listPurchase();
    Optional<Purchase> findPurchaseById(Long id);
    Purchase save(Purchase purchase);
    Optional<Purchase> updatePurchase(Long id, Purchase purchase);
    Optional<Purchase> deletePurchase(Long id);

}
