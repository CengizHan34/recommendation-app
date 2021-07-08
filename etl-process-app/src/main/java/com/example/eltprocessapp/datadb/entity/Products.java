package com.example.eltprocessapp.datadb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author created by cengizhan on 6.07.2021
 */
@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "category_id")
    private String categoryId;
}
