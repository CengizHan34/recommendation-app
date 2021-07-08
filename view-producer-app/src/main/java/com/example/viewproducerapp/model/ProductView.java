package com.example.viewproducerapp.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author created by cengizhan on 2.07.2021
 */
@Data
public class ProductView implements Serializable {
    private String event;
    private String messageid;
    private String userid;
    private Properties properties;
    private Context context;
}
