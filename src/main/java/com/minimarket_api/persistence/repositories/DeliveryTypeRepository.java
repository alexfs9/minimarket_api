package com.minimarket_api.persistence.repositories;

import com.minimarket_api.persistence.entities.DeliveryTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTypeRepository extends JpaRepository<DeliveryTypeEntity, Long> {
}
