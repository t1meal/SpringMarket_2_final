package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.core.entities.Category;
import ru.gb.market.core.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category findCategoryByTitle(String title){
        return categoryRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Category with title " + title + " not found!"));
    }
}
