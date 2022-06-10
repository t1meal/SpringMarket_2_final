package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gb.market.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByPriceBetween(int minPrice, int maxPrice);
    List<Product> findProductByPriceLessThanEqual (int maxPrice);
    List<Product> findProductByPriceGreaterThanEqual (int minPrice);

}
