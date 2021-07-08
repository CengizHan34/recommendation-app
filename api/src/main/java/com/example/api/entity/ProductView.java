package com.example.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author created by cengizhan on 2.07.2021
 */
@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductView implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String event;
    private String messageid;
    private String userid;
    @Embedded
    private Properties properties;
    @Embedded
    private Context context;
    private LocalDateTime createdDate = LocalDateTime.now();
}
