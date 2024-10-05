package com.store.back.infrastructure.controllers;

import com.store.back.application.service.IPurchaseService;
import com.store.back.domain.entities.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController {
    @Autowired
    IPurchaseService purchaseService;

    @GetMapping
    public List<Purchase> getAllCategories() {
        return purchaseService.listPurchase();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Purchase> purchaseOptional = purchaseService.findPurchaseById(id);
        if (purchaseOptional.isPresent()) {
            return ResponseEntity.ok(purchaseOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Purchase purchase) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.save(purchase));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Purchase purchase) {
        Optional<Purchase> purchaseOptional = purchaseService.findPurchaseById(id);
        if (purchaseOptional.isPresent()) {
            purchase.setId(id);
            return ResponseEntity.ok(purchaseService.save(purchase));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Purchase> purchaseOptional = purchaseService.findPurchaseById(id);
        if (purchaseOptional.isPresent()) {
            purchaseService.deletePurchase(id);
            return ResponseEntity.ok(purchaseOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
