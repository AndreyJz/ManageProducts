package com.store.back.infrastructure.controllers;

import com.store.back.application.service.IPurchaseProductService;
import com.store.back.domain.entities.PurchaseProduct;
import com.store.back.domain.entities.PurchaseProductPk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/purchaseproduct")
public class PurchaseProductController {
    @Autowired
    IPurchaseProductService purchaseProductService;

    @GetMapping
    public List<PurchaseProduct> getAllCategories() {
        return purchaseProductService.listPurchaseProduct();
    }

    @GetMapping("/{purchaseId}/{productId}")
    public ResponseEntity<?> view(@PathVariable Long purchaseId, @PathVariable Long productId) {
        PurchaseProductPk purchaseProductPk = new PurchaseProductPk(purchaseId,productId);
        Optional<PurchaseProduct> purchaseProductOptional = purchaseProductService.findPurchaseProductById(purchaseProductPk);
        if (purchaseProductOptional.isPresent()) {
            return ResponseEntity.ok(purchaseProductOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PurchaseProduct purchaseProduct) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseProductService.save(purchaseProduct));
    }

    @PutMapping("/{purchaseId}/{productId}")
    public ResponseEntity<?> update(@PathVariable Long purchaseId, @PathVariable Long productId, @RequestBody PurchaseProduct purchaseProduct) {
        PurchaseProductPk purchaseProductPk = new PurchaseProductPk(purchaseId,productId);
        Optional<PurchaseProduct> purchaseProductOptional = purchaseProductService.findPurchaseProductById(purchaseProductPk);
        if (purchaseProductOptional.isPresent()) {
            purchaseProduct.setId(purchaseProductPk);
            return ResponseEntity.ok(purchaseProductService.save(purchaseProduct));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{purchaseId}/{productId}")
    public ResponseEntity<?> delete(@PathVariable Long purchaseId, @PathVariable Long productId) {
        PurchaseProductPk purchaseProductPk = new PurchaseProductPk(purchaseId,productId);
        Optional<PurchaseProduct> purchaseProductOptional = purchaseProductService.findPurchaseProductById(purchaseProductPk);
        if (purchaseProductOptional.isPresent()) {
            purchaseProductService.deletePurchaseProduct(purchaseProductPk);
            return ResponseEntity.ok(purchaseProductOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
