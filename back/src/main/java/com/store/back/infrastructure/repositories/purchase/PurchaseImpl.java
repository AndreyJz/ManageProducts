package com.store.back.infrastructure.repositories.purchase;

import com.store.back.application.service.IPurchaseService;
import com.store.back.domain.entities.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseImpl implements IPurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Purchase> listPurchase() {
        return (List<Purchase>) purchaseRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Purchase> findPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    @Transactional
    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Transactional
    @Override
    public Optional<Purchase> updatePurchase(Long id, Purchase purchase) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findById(id);
        if (purchaseOptional.isPresent()) {
            Purchase purchaseToUpdate = purchaseRepository.save(purchase);
            purchaseToUpdate.setCustomers(purchase.getCustomers());
            purchaseToUpdate.setDate(purchase.getDate());
            purchaseToUpdate.setPayment(purchase.getPayment());
            purchaseToUpdate.setComment(purchase.getComment());
            purchaseToUpdate.setStatus(purchase.getStatus());
            return Optional.of(purchaseRepository.save(purchaseToUpdate));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Purchase> deletePurchase(Long id) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findById(id);
        if (purchaseOptional.isPresent()) {
            purchaseRepository.delete(purchaseOptional.get());
        }
        return Optional.empty();
    }
}
