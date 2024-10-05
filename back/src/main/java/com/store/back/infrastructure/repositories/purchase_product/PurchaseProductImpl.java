package com.store.back.infrastructure.repositories.purchase_product;

import com.store.back.application.service.IPurchaseProductService;
import com.store.back.domain.entities.PurchaseProduct;
import com.store.back.domain.entities.PurchaseProductPk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseProductImpl implements IPurchaseProductService {

    @Autowired
    private PurchaseProductRepository purchaseProductRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PurchaseProduct> listPurchaseProduct() {
        return (List<PurchaseProduct>) purchaseProductRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PurchaseProduct> findPurchaseProductById(PurchaseProductPk id) {
        return purchaseProductRepository.findById(id);
    }

    @Transactional
    @Override
    public PurchaseProduct save(PurchaseProduct purchaseProduct) {
        return purchaseProductRepository.save(purchaseProduct);
    }

    @Transactional
    @Override
    public Optional<PurchaseProduct> updatePurchaseProduct(PurchaseProductPk id, PurchaseProduct purchaseProduct) {
        Optional<PurchaseProduct> purchaseProductOptional = purchaseProductRepository.findById(id);
        if (purchaseProductOptional.isPresent()) {
            PurchaseProduct purchaseProductToUpdate = purchaseProductRepository.save(purchaseProduct);
            purchaseProductToUpdate.setAmount(purchaseProduct.getAmount());
            purchaseProductToUpdate.setTotal(purchaseProduct.getTotal());
            purchaseProductToUpdate.setStatus(purchaseProduct.getStatus());
            return Optional.of(purchaseProductRepository.save(purchaseProductToUpdate));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<PurchaseProduct> deletePurchaseProduct(PurchaseProductPk id) {
        Optional<PurchaseProduct> purchaseProductOptional = purchaseProductRepository.findById(id);
        if (purchaseProductOptional.isPresent()) {
            purchaseProductRepository.delete(purchaseProductOptional.get());
        }
        return Optional.empty();
    }
}
