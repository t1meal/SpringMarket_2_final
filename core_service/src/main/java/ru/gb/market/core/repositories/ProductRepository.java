package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.gb.market.core.entities.ProductEntity;


public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
