package com.store.back.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "purchase_products")
@Data
public class PurchaseProduct {
    @EmbeddedId
    @Column
    private PurchaseProductPk id;

    @ManyToOne
    @MapsId("purchaseId")
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private float total;

    @Column(nullable = false)
    private int status;

}
