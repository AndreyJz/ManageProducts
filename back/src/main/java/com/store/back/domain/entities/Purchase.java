package com.store.back.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "purchases")
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Customer customers;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Character payment;

    @Column(nullable = true)
    private String comment;

    @Column(nullable = false)
    private Character status;
}
