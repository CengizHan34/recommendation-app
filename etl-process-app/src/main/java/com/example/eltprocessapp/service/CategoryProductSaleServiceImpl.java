package com.example.eltprocessapp.service;

import com.example.eltprocessapp.datadb.dao.ProductDao;
import com.example.eltprocessapp.dto.CategoryProductSaleDto;
import com.example.eltprocessapp.postgres.entity.CategoryProductSale;
import com.example.eltprocessapp.postgres.repository.CategoryProductSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author created by cengizhan on 7.07.2021
 */
@Service
@RequiredArgsConstructor
public class CategoryProductSaleServiceImpl implements CategoryProductSaleService {
    private final ProductDao productDao;
    private final CategoryProductSaleRepository categoryProductSaleRepository;

    @Override
    public void saveCategoryProductSalesListToPostgresDatabase() {
        List<CategoryProductSaleDto> categoryProductSaleDtoList = productDao.getCategoryProductSaleDtoList();

        List<CategoryProductSale> categoryProductSaleList = categoryProductSaleDtoList.stream()
                .map(dto -> CategoryProductSale.builder()
                        .categoryId(dto.getCategory_id()).productId(dto.getProduct_id())
                        .districtUserCount(dto.getDistinct_user_count()).build())
                .collect(Collectors.toList());
        categoryProductSaleRepository.saveAll(categoryProductSaleList);
    }
}
