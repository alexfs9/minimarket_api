package com.minimarket_api.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sale_detail")
@Getter
@Setter
public class SaleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "saleId")
    private SaleEntity sale;

    @OneToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;

    @Column(columnDefinition = "TINYINT")
    private int quantity;

    private float price;
}
