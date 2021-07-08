package com.example.eltprocessapp.postgres.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author created by cengizhan on 7.07.2021
 */
@Data
@Entity
@Builder
@Table(name = "category_product_sale", indexes = {@Index(name = "i_category_product", columnList = "category_id, product_id")})
@NoArgsConstructor
@AllArgsConstructor

public class CategoryProductSale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "district_user_count")
    private Integer districtUserCount;
}
