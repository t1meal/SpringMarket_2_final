package ru.gb.market.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {
//
//    ProductDto mapToDto (ProductEntity product);
//
//    ProductEntity mapToProduct (ProductDto productDto);
}
