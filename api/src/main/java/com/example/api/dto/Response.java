package com.example.api.dto;

import com.example.api.enums.CustomerType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author created by cengizhan on 6.07.2021
 */
@Data
@Builder
public class Response {
    private String userId;
    private List<String> products;
    private CustomerType type;
}
