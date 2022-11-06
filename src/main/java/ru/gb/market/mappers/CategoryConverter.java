package ru.gb.market.mappers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.dto.CategoryDto;
import ru.gb.market.entities.Category;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final ProductConverter productConverter ;

    public CategoryDto entityToDto (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setProducts(category.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));
        return categoryDto;
    }



}
