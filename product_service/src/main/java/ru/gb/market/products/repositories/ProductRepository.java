package ru.gb.market.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.products.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
