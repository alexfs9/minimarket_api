package com.minimarket_api.persistence.repositories;

import com.minimarket_api.persistence.entities.DeliveryTypeEntity;
import com.minimarket_api.persistence.enums.DeliveryTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryTypeRepository extends JpaRepository<DeliveryTypeEntity, Long> {

    Optional<DeliveryTypeEntity> findByDeliveryType(DeliveryTypeEnum deliveryType);
}
