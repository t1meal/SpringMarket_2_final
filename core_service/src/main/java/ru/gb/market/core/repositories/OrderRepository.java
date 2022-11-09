package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.core.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
