package com.project.full.domain.repository;

import com.project.full.domain.entity.ChemicalProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemicalProductRepository extends JpaRepository<ChemicalProducts, Long> {
}
