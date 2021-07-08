package com.example.api.repository;

import com.example.api.entity.CategoryProductSale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author created by cengizhan on 7.07.2021
 */
public interface CategoryProductSaleRepository extends JpaRepository<CategoryProductSale, Long> {
    Page<CategoryProductSale> findByProductIdIn(List<String> products, Pageable pageable);

    Page<CategoryProductSale> findByCategoryIdIn(List<String> categories, Pageable pageable);
}
