package service;

import com.example.api.dto.Response;
import com.example.api.entity.CategoryProductSale;
import com.example.api.entity.Context;
import com.example.api.entity.ProductView;
import com.example.api.entity.Properties;
import com.example.api.enums.CustomerType;
import com.example.api.repository.CategoryProductSaleRepository;
import com.example.api.repository.ProductViewRepository;
import com.example.api.service.ProductViewService;
import com.example.api.service.impl.ProductViewServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author created by cengizhan on 7.07.2021
 */
@ExtendWith(MockitoExtension.class)
public class ProductViewServiceImplTest {
    private ProductViewService target;
    @Mock
    private ProductViewRepository productViewRepository;
    @Mock
    private CategoryProductSaleRepository categoryProductSaleRepository;

    private List<ProductView> productViewList;
    private List<CategoryProductSale> categoryProductSaleList;
    private List<CategoryProductSale> categoryProductList;

    @BeforeEach
    void init() {
        target = new ProductViewServiceImpl(productViewRepository, categoryProductSaleRepository);
        Properties properties = new Properties("product-12");
        Context context = new Context("Mobil-App");
        ProductView productView1 = new ProductView(1l, "ProductView", "asda-sdsd-12s", "user-53",
                properties, context, LocalDateTime.now());

        properties = new Properties("product-25");
        context = new Context("web");
        ProductView productView2 = new ProductView(2l, "ProductView", "323-sdsd-12s", "user-53",
                properties, context, LocalDateTime.now());

        properties = new Properties("product-34");
        context = new Context("web");
        ProductView productView3 = new ProductView(3l, "ProductView", "323-sdsd-12s", "user-53",
                properties, context, LocalDateTime.now());

        productViewList = Arrays.asList(productView1, productView2, productView3);

        CategoryProductSale categoryProductSale1 = new CategoryProductSale(1l, "category-1", "product-12", 65);
        CategoryProductSale categoryProductSale2 = new CategoryProductSale(2l, "category-2", "product-25", 62);
        CategoryProductSale categoryProductSale3 = new CategoryProductSale(3l, "category-1", "product-34", 60);
        CategoryProductSale categoryProductSale4 = new CategoryProductSale(4l, "category-4", "product-21", 58);

        categoryProductSaleList = new ArrayList<>() {
            {
                add(categoryProductSale1);
                add(categoryProductSale2);
                add(categoryProductSale3);
                add(categoryProductSale4);
            }

            ;
        };
        categoryProductList = Arrays.asList(categoryProductSale1, categoryProductSale2, categoryProductSale4);
    }

    @Test
    public void getLastTenProductsViewedForUser_ifMethodIsCalled_shouldReturnViewedProductList() {
        Page<ProductView> productViewPage = new PageImpl<>(productViewList);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdDate").descending());
        when(productViewRepository.findByUserid("user-53", pageable)).thenReturn(productViewPage);
        Response result = target.getLastTenProductsViewedForUser("user-53");
        Assertions.assertEquals(result.getProducts().size(), 3);
        Assertions.assertEquals(result.getType(), CustomerType.PERSONALIZED);
    }

    @Test
    public void deleteUserHistory_productHistoryShouldBeDeleted() {
        target.deleteUserHistory("user-53", "product-12");
        verify(productViewRepository, times(1)).deleteUserHistory(anyString(), anyString());
    }

    @Test
    public void getBestSellerProductsForUser_ifAllConditionsAreMet_shouldReturnBestSellerProducts() {
        CategoryProductSale categoryProductSale5 = new CategoryProductSale(5l, "category-1", "product-66", 55);
        CategoryProductSale categoryProductSale6 = new CategoryProductSale(6l, "category-4", "product-78", 52);
        categoryProductSaleList.add(categoryProductSale5);
        categoryProductSaleList.add(categoryProductSale6);

        List<String> productIds = Arrays.asList("product-12", "product-25", "product-34");
        List<String> categoryIds = Arrays.asList("category-1", "category-2", "category-4");

        Pageable pageableCategory = PageRequest.of(0, 3, Sort.by("districtUserCount").descending());
        Pageable pageableProduct = PageRequest.of(0, 10, Sort.by("districtUserCount").descending());

        Page<CategoryProductSale> categoryProductSaleCategoryPage = new PageImpl<>(categoryProductList);
        Page<CategoryProductSale> categoryProductSaleProductPage = new PageImpl<>(categoryProductSaleList);

        when(productViewRepository.findByUserid(anyString())).thenReturn(productViewList);

        when(categoryProductSaleRepository.findByProductIdIn(productIds, pageableCategory))
                .thenReturn(categoryProductSaleCategoryPage);

        when(categoryProductSaleRepository.findByCategoryIdIn(categoryIds, pageableProduct))
                .thenReturn(categoryProductSaleProductPage);

        Response result = target.getBestSellerProductsForUser(anyString());
        Assertions.assertEquals(result.getProducts().size(), 6);
        Assertions.assertEquals(result.getType(), CustomerType.PERSONALIZED);
    }

    @Test
    public void getBestSellerProductsForUser_ifNumberOfTopSellingProductsIsLessThanFive_shouldReturnEmptyList() {
        List<String> productIds = Arrays.asList("product-12", "product-25", "product-34");
        List<String> categoryIds = Arrays.asList("category-1", "category-2", "category-4");

        Pageable pageableCategory = PageRequest.of(0, 3, Sort.by("districtUserCount").descending());
        Pageable pageableProduct = PageRequest.of(0, 10, Sort.by("districtUserCount").descending());

        Page<CategoryProductSale> categoryProductSaleCategoryPage = new PageImpl<>(categoryProductList);
        Page<CategoryProductSale> categoryProductSaleProductPage = new PageImpl<>(categoryProductSaleList);

        when(productViewRepository.findByUserid(anyString())).thenReturn(productViewList);

        when(categoryProductSaleRepository.findByProductIdIn(productIds, pageableCategory))
                .thenReturn(categoryProductSaleCategoryPage);

        when(categoryProductSaleRepository.findByCategoryIdIn(categoryIds, pageableProduct))
                .thenReturn(categoryProductSaleProductPage);

        Response result = target.getBestSellerProductsForUser(anyString());
        Assertions.assertEquals(result.getProducts().size(), 0);
        Assertions.assertEquals(result.getType(), CustomerType.PERSONALIZED);
    }

    @Test
    public void getBestSellerProductsForUser_ifThereIsNoProductViewedInPast_shouldReturnGeneralBestSellers() {
        CategoryProductSale categoryProductSale5 = new CategoryProductSale(5l, "category-1", "product-66", 55);
        CategoryProductSale categoryProductSale6 = new CategoryProductSale(6l, "category-4", "product-78", 52);
        categoryProductSaleList.add(categoryProductSale5);
        categoryProductSaleList.add(categoryProductSale6);

        Pageable pageableProduct = PageRequest.of(0, 10, Sort.by("districtUserCount").descending());
        Page<CategoryProductSale> categoryProductSaleProductPage = new PageImpl<>(categoryProductSaleList);

        when(productViewRepository.findByUserid(anyString())).thenReturn(new ArrayList<>());
        when(categoryProductSaleRepository.findAll(pageableProduct)).thenReturn(categoryProductSaleProductPage);

        Response result = target.getBestSellerProductsForUser(anyString());
        Assertions.assertEquals(result.getProducts().size(), 6);
        Assertions.assertEquals(result.getType(), CustomerType.NON_PERSONALIZED);
    }

}
