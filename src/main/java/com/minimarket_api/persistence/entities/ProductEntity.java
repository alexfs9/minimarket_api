package com.minimarket_api.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 150)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "subcategoryId")
    private SubcategoryEntity subcategory;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierId")
    private SupplierEntity supplier;

    @NotBlank
    private float price;

    @NotBlank
    @Column(columnDefinition = "TINYINT")
    private int stock;

    @NotBlank
    private float discount;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean enable;

    @NotBlank
    private String image;
}
