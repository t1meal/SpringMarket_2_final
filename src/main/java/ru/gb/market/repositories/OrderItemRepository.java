package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository <OrderItem, Long> {
}
