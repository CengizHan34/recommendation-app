package com.example.api.controller;

import com.example.api.service.ProductViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author created by cengizhan on 5.07.2021
 */
@RestController
@RequestMapping("/product")
public class ProductViewController {
    @Autowired
    private ProductViewService productViewService;

    @GetMapping("/browsing-history/{userId}")
    public ResponseEntity getUserHistory(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(productViewService.getLastTenProductsViewedForUser(userId));
    }

    @DeleteMapping("/browsing-history/{userId}/{productId}")
    public ResponseEntity deleteUserHistory(@PathVariable("userId") String userId,
                                            @PathVariable("productId") String productId) {
        productViewService.deleteUserHistory(userId, productId);
        return ResponseEntity.ok().body("Success!");
    }

    @GetMapping("/best-seller-products/{userId}")
    public ResponseEntity getBestSellerProducts(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(productViewService.getBestSellerProductsForUser(userId));
    }
}
