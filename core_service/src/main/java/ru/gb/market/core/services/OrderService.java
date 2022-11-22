package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.api.dto.CartDto;
import ru.gb.market.core.entities.Order;
import ru.gb.market.core.entities.OrderItem;
import ru.gb.market.core.integrations.CartServiceIntegration;
import ru.gb.market.core.mappers.CartItemDtoMapper;
import ru.gb.market.core.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartItemDtoMapper cartItemDtoMapper;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(String userName) {
        CartDto cartDto = cartServiceIntegration.getUserCart(userName);

        Order order = new Order();
        order.setUsername(userName);
        order.setTotalPrice(cartDto.getTotalPrice());

        List<OrderItem> items = cartItemDtoMapper.mapToOrderItems(cartDto.getItems());
        List<OrderItem> itemList = cartItemDtoMapper.setOrder(items, order);

        order.setItems(itemList);
        order.setUser_email("user@yandex.ru");

        orderRepository.save(order);
    }
}
