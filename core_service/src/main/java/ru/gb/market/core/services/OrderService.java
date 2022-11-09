package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.api.dto.CartDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.core.entities.Order;
import ru.gb.market.core.entities.OrderItem;
import ru.gb.market.core.entities.UserEntity;
import ru.gb.market.core.integrations.CartServiceIntegration;
import ru.gb.market.core.mappers.CartItemDtoMapper;
import ru.gb.market.core.repositories.OrderItemRepository;
import ru.gb.market.core.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemDtoMapper cartItemDtoMapper;
    private final UserService userService;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(String userName) {
        UserEntity user = userService.findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User name " + userName + "not found!"));
        CartDto cartDto = cartServiceIntegration.getCartById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart for user id " + user.getId() + " is not found!"));

        List<OrderItem> items = cartItemDtoMapper.mapToOrderItems(cartDto.getItems());

        Order order = new Order(user, cartDto.getTotalPrice(), items);
        orderRepository.save(order);
        orderItemRepository.saveAll(cartItemDtoMapper.setOrder(items, order));
        cartServiceIntegration.clearCart(cartDto.getId());
    }
}
