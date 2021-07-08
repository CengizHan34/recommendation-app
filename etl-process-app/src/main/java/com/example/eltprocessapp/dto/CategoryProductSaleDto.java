package com.example.eltprocessapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author created by cengizhan on 7.07.2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProductSaleDto {
    private String category_id;
    private String product_id;
    private Integer distinct_user_count;
}
