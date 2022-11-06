package ru.gb.market.mappers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.entities.Category;
import ru.gb.market.entities.ProductEntity;
import ru.gb.market.services.CategoryService;


@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final CategoryService categoryService;

    public ProductDto entityToDto (ProductEntity product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryTitle(product.getCategory().getTitle());
        return productDto;
    }

    public ProductEntity dtoToEntity (ProductDto productDto){
        ProductEntity product = new ProductEntity();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category c =  categoryService.findCategoryByTitle(productDto.getCategoryTitle());
        product.setCategory(c);
        return product;
    }

}
