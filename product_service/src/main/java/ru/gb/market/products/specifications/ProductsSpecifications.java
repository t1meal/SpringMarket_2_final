package ru.gb.market.products.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.market.products.entities.ProductEntity;

public class ProductsSpecifications {
    public static Specification<ProductEntity> priceGreaterOrEqualsThan (Integer price){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<ProductEntity> priceLessThanOrEqualsThan (Integer price){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }
    public static Specification<ProductEntity> titleLike (String title){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title)));
    }
}
