package ru.gb.market.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.market.products.entities.CategoryEntity;
import ru.gb.market.products.repositories.CategoryRepository;


import java.util.Collections;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAllCategoriesTest() {
        CategoryEntity category = new CategoryEntity();
        category.setTitle("Электроника");
        category.setProducts(Collections.emptyList());
        entityManager.persist(category);
        entityManager.flush();

        List<CategoryEntity> categoriesList = categoryRepository.findAll();
        Assertions.assertEquals(4, categoriesList.size());
        Assertions.assertEquals("Еда", categoriesList.get(0).getTitle());
    }
}
