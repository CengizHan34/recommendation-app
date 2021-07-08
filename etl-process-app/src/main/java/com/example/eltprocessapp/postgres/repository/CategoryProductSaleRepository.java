package com.example.eltprocessapp.postgres.repository;

import com.example.eltprocessapp.postgres.entity.CategoryProductSale;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author created by cengizhan on 7.07.2021
 */
public interface CategoryProductSaleRepository extends JpaRepository<CategoryProductSale, Long> {
}
