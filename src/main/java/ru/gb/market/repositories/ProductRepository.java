package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.model.Product;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByTitle(String title);

}
