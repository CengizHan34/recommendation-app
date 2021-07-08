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
@Table(name = "order_items")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "order_id")
    private Long orderId;
}
