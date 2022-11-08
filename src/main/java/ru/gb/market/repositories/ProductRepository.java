package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.gb.market.entities.ProductEntity;


public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
