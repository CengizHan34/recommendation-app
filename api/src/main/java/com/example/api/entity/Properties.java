package com.example.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author created by cengizhan on 2.07.2021
 */
@Data
@Embeddable
@AllArgsConstructor
public class Properties implements Serializable {
    private String productid;
}
