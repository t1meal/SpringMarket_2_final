package ru.gb.market.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.gb.market.entities.Product;
import ru.gb.market.dto.ProductDto;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    ProductDto mapToDto (Product product);

    Product mapToProduct (ProductDto productDto);
}
