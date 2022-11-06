package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.entities.Category;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category findCategoryByTitle(String title){
        return categoryRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Category with title " + title + " not found!"));
    }
}
