package com.example.api.service;

import com.example.api.dto.Response;

/**
 * @author created by cengizhan on 5.07.2021
 */
public interface ProductViewService {
    Response getLastTenProductsViewedForUser(String userId);

    void deleteUserHistory(String userId, String productId);

    Response getBestSellerProductsForUser(String userId);


}
