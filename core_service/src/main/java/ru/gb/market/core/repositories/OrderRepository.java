package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.core.entities.Order;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsername (String username);
}
