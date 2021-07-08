package com.example.api.repository;

import com.example.api.entity.ProductView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author created by cengizhan on 5.07.2021
 */
public interface ProductViewRepository extends JpaRepository<ProductView, Long> {
    List<ProductView> findByUserid(String userId);

    Page<ProductView> findByUserid(String userid, Pageable pageable);

    @Modifying
    @Transactional
    @Query("delete from ProductView pw where pw.userid = :userId and pw.properties.productid = :productId")
    void deleteUserHistory(@Param("userId") String userId, @Param("productId") String productId);

}
