package ru.gb.market.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.entities.Order;
import ru.gb.market.entities.OrderItem;
import ru.gb.market.models.CartItem;
import ru.gb.market.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CartToOrderItemMapper {

    private final ProductService productService;

    public List<OrderItem> mapToOrderItems(List<CartItem> items) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : items) {
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
