package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.entities.Order;
import ru.gb.market.entities.OrderItem;
import ru.gb.market.entities.User;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.mappers.CartToOrderItemMapper;
import ru.gb.market.models.ShoppingCart;
import ru.gb.market.repositories.OrderItemRepository;
import ru.gb.market.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartToOrderItemMapper cartToOrderItemMapper;
    private final UserService userService;
    private final CartServiceUtils cartServiceUtils;

    public void createOrder(String name) {
        ShoppingCart cart = cartServiceUtils.findCartById(cartServiceUtils.pullUserId(name));
        User user = userService.findByUsername(name).orElseThrow(() -> new ResourceNotFoundException("User name " + name + "not found!"));
        List<OrderItem> items = cartToOrderItemMapper.mapToOrderItems(cart.getItems());

        Order order = new Order(user, cart, items);
        orderRepository.save(order);
        orderItemRepository.saveAll(cartToOrderItemMapper.setOrder(items, order));
    }
}
