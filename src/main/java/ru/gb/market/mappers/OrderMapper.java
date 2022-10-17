package ru.gb.market.mappers;

import org.springframework.stereotype.Component;
import ru.gb.market.models.CartItem;

import java.util.List;

@Component
public class OrderMapper {

    public String mapToOrderItems(List<CartItem> items) {
        StringBuilder buffer = new StringBuilder("Order consist of:");

        for (CartItem cartItem : items) {
            buffer.append(cartItem.toString());
        }
        return buffer.toString();
    }
    
    
}
