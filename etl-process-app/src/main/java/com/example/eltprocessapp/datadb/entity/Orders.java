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
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id")
    private String userId;
}
