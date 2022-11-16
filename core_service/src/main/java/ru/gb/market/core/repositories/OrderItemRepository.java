package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.core.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository <OrderItem, Long> {
}
