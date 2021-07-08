package com.example.streamreaderapp.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author created by cengizhan on 2.07.2021
 */
@Data
@Embeddable
public class Context implements Serializable {
    private String source;
}
