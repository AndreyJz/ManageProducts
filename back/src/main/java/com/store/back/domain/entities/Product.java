package com.store.back.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Category categories;

    @Column(nullable = true)
    private String barCode;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private float stock;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private float price;

    @Column(nullable = false)
    private int status;

    @Column(nullable = true)
    private String image;
}
