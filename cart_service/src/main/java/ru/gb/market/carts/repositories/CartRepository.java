package ru.gb.market.carts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.market.carts.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
