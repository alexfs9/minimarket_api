package com.minimarket_api.persistence.repositories;

import com.minimarket_api.persistence.entities.PayMethodEntity;
import com.minimarket_api.persistence.enums.PayMethodEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethodEntity, Long> {

    Optional<PayMethodEntity> findByPayMethod(PayMethodEnum payMethod);
}
