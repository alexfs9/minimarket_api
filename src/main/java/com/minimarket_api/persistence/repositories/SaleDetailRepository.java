package com.minimarket_api.persistence.repositories;

import com.minimarket_api.persistence.entities.SaleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetailEntity, Long> {
}
