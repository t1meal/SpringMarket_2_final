package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.entities.Order;
import ru.gb.market.entities.User;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.mappers.OrderMapper;
import ru.gb.market.models.ShoppingCart;
import ru.gb.market.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final CartServiceUtils cartServiceUtils;

    public void saveOrder(String name) {
        Long id = cartServiceUtils.pullUserId(name);
        ShoppingCart cart = cartServiceUtils.findCartById(id);
        User user = userService.findByUsername(name).orElseThrow(() -> new ResourceNotFoundException("User name " + name + "not found!"));

        Order userOrder = new Order();
        userOrder.setTotalPrice(cart.getTotalPrice());
        userOrder.setOrderItems(orderMapper.mapToOrderItems(cart.getItems()));
        userOrder.setUser(user);
        orderRepository.save(userOrder);
    }
}
