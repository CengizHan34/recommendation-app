package service;

import com.example.eltprocessapp.datadb.dao.ProductDao;
import com.example.eltprocessapp.dto.CategoryProductSaleDto;
import com.example.eltprocessapp.postgres.entity.CategoryProductSale;
import com.example.eltprocessapp.postgres.repository.CategoryProductSaleRepository;
import com.example.eltprocessapp.service.CategoryProductSaleService;
import com.example.eltprocessapp.service.CategoryProductSaleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * @author created by cengizhan on 8.07.2021
 */
@ExtendWith(MockitoExtension.class)
public class CategoryProductSaleServiceImplTest {
    private CategoryProductSaleService target;
    @Mock
    private ProductDao productDao;
    @Mock
    private CategoryProductSaleRepository categoryProductSaleRepository;

    @BeforeEach
    void init() {
        target = new CategoryProductSaleServiceImpl(productDao, categoryProductSaleRepository);
    }

    @Test
    public void saveCategoryProductSalesListToPostgresDatabase_whenMethodIsCalled_shouldSaveCategoryProductSaleListToProgresDB() {
        CategoryProductSaleDto categoryProductSaleDto1 = new CategoryProductSaleDto("category-12", "product-459", 60);
        CategoryProductSaleDto categoryProductSaleDto2 = new CategoryProductSaleDto("category-4", "product-33", 50);

        CategoryProductSale categoryProductSale1 = CategoryProductSale.builder().productId("product-459").categoryId("category-12").districtUserCount(60).build();
        CategoryProductSale categoryProductSale2 = CategoryProductSale.builder().productId("product-33").categoryId("category-4").districtUserCount(50).build();

        when(productDao.getCategoryProductSaleDtoList()).thenReturn(Arrays.asList(categoryProductSaleDto1, categoryProductSaleDto2));
        target.saveCategoryProductSalesListToPostgresDatabase();

        verify(categoryProductSaleRepository, times(1)).saveAll(Arrays.asList(categoryProductSale1, categoryProductSale2));
    }
}
