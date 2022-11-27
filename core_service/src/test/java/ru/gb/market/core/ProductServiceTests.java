package ru.gb.market.core;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.core.entities.Category;
import ru.gb.market.core.repositories.ProductRepository;
import ru.gb.market.core.services.CategoryService;
import ru.gb.market.core.services.ProductService;


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
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Food");
        category.setProducts(Collections.emptyList());

        Mockito.doReturn(category)
                .when(categoryService)
                .findCategoryByTitle("Food");

        ProductDto productDto = new ProductDto(null, "Апельсины", 100, "Food");
        productService.createNewProduct(productDto);

        Mockito.verify(productRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }
}
