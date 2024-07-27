package com.minimarket_api.services;

import com.minimarket_api.persistence.entities.DeliveryTypeEntity;
import com.minimarket_api.persistence.enums.DeliveryTypeEnum;
import com.minimarket_api.persistence.repositories.DeliveryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryTypeService {

    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;

    public Optional<DeliveryTypeEntity> findByDeliveryType(DeliveryTypeEnum deliveryType) {
        return this.deliveryTypeRepository.findByDeliveryType(deliveryType);
    }

    public DeliveryTypeEntity save(DeliveryTypeEntity deliveryType) {
        return this.deliveryTypeRepository.save(deliveryType);
    }
}
