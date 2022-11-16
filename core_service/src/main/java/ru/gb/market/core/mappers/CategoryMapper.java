package ru.gb.market.core.mappers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.CategoryDto;
import ru.gb.market.core.entities.Category;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ProductConverter productConverter ;

    public CategoryDto entityToDto (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setProducts(category.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));
        return categoryDto;
    }



}
