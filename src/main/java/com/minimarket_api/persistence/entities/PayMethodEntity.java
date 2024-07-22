package com.minimarket_api.persistence.entities;

import com.minimarket_api.persistence.enums.PayMethodEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pay_method")
@Getter
@Setter
public class PayMethodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @Enumerated(EnumType.STRING)
    private PayMethodEnum payMethod;
}
