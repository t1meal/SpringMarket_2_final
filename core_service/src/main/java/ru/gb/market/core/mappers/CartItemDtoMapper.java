package ru.gb.market.core.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.CartItemDto;
import ru.gb.market.core.entities.Order;
import ru.gb.market.core.entities.OrderItem;
import ru.gb.market.core.integrations.ProductServiceIntegration;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CartItemDtoMapper {

    private final ProductServiceIntegration productServiceIntegration;

    public List<OrderItem> mapToOrderItems(List<CartItemDto> cartItems, Order order) {

        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> new OrderItem(
                        productServiceIntegration.getProductById(cartItem.getProductId()).getTitle(),
                        cartItem.getCount(),
                        cartItem.getPrice(),
                        cartItem.getSum())
                ).collect(Collectors.toList());

        orderItems.forEach(orderItem -> orderItem.setOrder(order));

        return orderItems;
    }

}
