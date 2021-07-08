package com.example.api.service.impl;

import com.example.api.dto.Response;
import com.example.api.entity.CategoryProductSale;
import com.example.api.entity.ProductView;
import com.example.api.enums.CustomerType;
import com.example.api.repository.CategoryProductSaleRepository;
import com.example.api.repository.ProductViewRepository;
import com.example.api.service.ProductViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author created by cengizhan on 5.07.2021
 */
@Service
@RequiredArgsConstructor
public class ProductViewServiceImpl implements ProductViewService {
    private final ProductViewRepository productViewRepository;
    private final CategoryProductSaleRepository categoryProductSaleRepository;

    @Override
    public Response getLastTenProductsViewedForUser(String userId) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdDate").descending());
        Page<ProductView> productViews = productViewRepository.findByUserid(userId, pageable);

        List<String> products = productViews.stream().map(product -> product.getProperties().getProductid())
                .collect(Collectors.toList());

        return Response.builder().userId(userId).products(products).type(CustomerType.PERSONALIZED).build();
    }

    @Override
    public void deleteUserHistory(String userId, String productId) {
        productViewRepository.deleteUserHistory(userId, productId);
    }

    @Override
    public Response getBestSellerProductsForUser(String userId) {
        List<ProductView> productViewList = productViewRepository.findByUserid(userId);

        if (ObjectUtils.isEmpty(productViewList)) {
            Page<CategoryProductSale> categoryProductSalePage = categoryProductSaleRepository
                    .findAll(PageRequest.of(0, 10, Sort.by("districtUserCount").descending()));

            List<String> bestProducts = categoryProductSalePage.getContent().stream()
                    .map(CategoryProductSale::getProductId)
                    .collect(Collectors.toList());
            return Response.builder().products(bestProducts)
                    .type(CustomerType.NON_PERSONALIZED).userId(userId).build();
        }

        List<String> productIdList = productViewList.stream()
                .map(productView -> productView.getProperties().getProductid())
                .collect(Collectors.toList());

        Page<CategoryProductSale> mostPurchasedCategories = categoryProductSaleRepository.findByProductIdIn(productIdList,
                PageRequest.of(0, 3, Sort.by("districtUserCount").descending()));

        List<String> categoryIdList = mostPurchasedCategories.stream()
                .map(CategoryProductSale::getCategoryId)
                .collect(Collectors.toList());

        Page<CategoryProductSale> mostPurchasedProducts = categoryProductSaleRepository.findByCategoryIdIn(categoryIdList,
                PageRequest.of(0, 10, Sort.by("districtUserCount").descending()));

        List<String> bestProducts = mostPurchasedProducts.getContent().stream()
                .map(CategoryProductSale::getProductId)
                .collect(Collectors.toList());

        if (bestProducts.size() < 5) {
            return Response.builder().products(new ArrayList<>()).userId(userId).type(CustomerType.PERSONALIZED).build();
        }

        return Response.builder().products(bestProducts).userId(userId).type(CustomerType.PERSONALIZED).build();
    }
}
