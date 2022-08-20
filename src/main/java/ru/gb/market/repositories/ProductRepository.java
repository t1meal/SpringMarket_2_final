package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.gb.market.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
