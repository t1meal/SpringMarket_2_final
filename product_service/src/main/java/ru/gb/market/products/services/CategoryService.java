package ru.gb.market.products.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.products.entities.CategoryEntity;
import ru.gb.market.products.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryEntity findCategoryByTitle(String title){
        return categoryRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Category with title " + title + " not found!"));
    }
}
