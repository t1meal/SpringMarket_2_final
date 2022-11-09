package ru.gb.market.core.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.CartItemDto;
import ru.gb.market.core.entities.Order;
import ru.gb.market.core.entities.OrderItem;
import ru.gb.market.core.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CartItemDtoMapper {

    private final ProductService productService;

    public List<OrderItem> mapToOrderItems(List<CartItemDto> items) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItemDto item : items) {
            OrderItem orderItem = new OrderItem(item.getCount(), item.getPrice(), item.getSum());
            orderItem.setProduct(productService.findProductById(item.getProductId()));
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    public List<OrderItem> setOrder(List<OrderItem> items, Order order){
        for (OrderItem item: items) {
            item.setOrder(order);
        }
        return items;
    }

}
