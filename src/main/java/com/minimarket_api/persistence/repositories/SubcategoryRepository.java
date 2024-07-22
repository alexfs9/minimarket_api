package com.minimarket_api.persistence.repositories;

import com.minimarket_api.persistence.entities.SubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubcategoryEntity, Long> {
}
