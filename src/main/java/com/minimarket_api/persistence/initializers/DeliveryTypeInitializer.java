package com.minimarket_api.persistence.initializers;

import com.minimarket_api.persistence.entities.DeliveryTypeEntity;
import com.minimarket_api.persistence.enums.DeliveryTypeEnum;
import com.minimarket_api.services.DeliveryTypeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeliveryTypeInitializer {

    @Autowired
    private DeliveryTypeService deliveryTypeService;

    @PostConstruct
    public void initializeDeliveryTypes() {
        for (DeliveryTypeEnum deliveryType : DeliveryTypeEnum.values()) {
            Optional<DeliveryTypeEntity> registeredDeliveryType = this.deliveryTypeService.findByDeliveryType(deliveryType);
            if (registeredDeliveryType.isEmpty()) {
                DeliveryTypeEntity deliveryTypeToRegister = new DeliveryTypeEntity();
                deliveryTypeToRegister.setDeliveryType(deliveryType);
                this.deliveryTypeService.save(deliveryTypeToRegister);
            }
        }
    }
}
