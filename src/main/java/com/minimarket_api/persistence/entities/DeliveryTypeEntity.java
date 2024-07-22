package com.minimarket_api.persistence.entities;

import com.minimarket_api.persistence.enums.DeliveryTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "delivery_type")
@Getter
@Setter
public class DeliveryTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, unique = true)
    @Enumerated(EnumType.STRING)
    private DeliveryTypeEnum deliveryType;
}
