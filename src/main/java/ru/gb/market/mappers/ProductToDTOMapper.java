package ru.gb.market.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.gb.market.entities.Product;
import ru.gb.market.models.ProductDto;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductToDTOMapper {

    ProductDto mapToDto (Product product);

    Product mapToProduct (ProductDto productDto);
}
