package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
