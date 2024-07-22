package com.minimarket_api.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "supplier")
@Getter
@Setter
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 40)
    private String name;

    @NotBlank
    @Column(length = 9)
    private String phone;

    @NotBlank
    @Column(length = 70)
    private String email;

    @NotBlank
    @Column(length = 150)
    private String address;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductEntity> products;
}
