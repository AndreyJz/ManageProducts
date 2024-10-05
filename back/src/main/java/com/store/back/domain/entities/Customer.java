package com.store.back.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private int phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "customers")
    @JsonIgnore
    private List<Purchase> purchases;
}
