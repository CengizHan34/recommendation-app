package com.example.streamreaderapp.repository;

import com.example.streamreaderapp.entity.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author created by cengizhan on 4.07.2021
 */
public interface ProductViewRepository extends JpaRepository<ProductView, Long> {
}
