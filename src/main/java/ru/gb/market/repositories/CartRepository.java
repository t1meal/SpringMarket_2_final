package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.market.models.ShoppingCart;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCart, Long> {

}
