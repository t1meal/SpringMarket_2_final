package ru.gb.market.products;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.products.entities.CategoryEntity;
import ru.gb.market.products.repositories.ProductRepository;
import ru.gb.market.products.services.CategoryService;
import ru.gb.market.products.services.ProductService;


import java.math.BigDecimal;
import java.util.Collections;

@SpringBootTest
public class ProductServiceTests {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void createNewProductTest() {
        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setTitle("Food");
        category.setProducts(Collections.emptyList());

        Mockito.doReturn(category)
                .when(categoryService)
                .findCategoryByTitle("Food");

        ProductDto productDto = new ProductDto(null, "Апельсины", BigDecimal.valueOf(100), "Food");
        productService.createNewProduct(productDto);

        Mockito.verify(productRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }
}
