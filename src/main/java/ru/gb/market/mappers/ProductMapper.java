package ru.gb.market.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.gb.market.entities.ProductEntity;
import ru.gb.market.dto.ProductDto;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    ProductDto mapToDto (ProductEntity product);

    ProductEntity mapToProduct (ProductDto productDto);
}
