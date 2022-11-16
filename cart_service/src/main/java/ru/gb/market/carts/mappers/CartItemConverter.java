package ru.gb.market.carts.mappers;

import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.CartItemDto;
import ru.gb.market.carts.models.CartItem;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto (CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setTitle(cartItem.getTitle());
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setCount(cartItem.getCount());
        cartItemDto.setSum(cartItem.getSum());
        return cartItemDto;
    }
    public CartItem dtoToEntity (CartItemDto cartItemDto){
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemDto.getProductId());
        cartItem.setTitle(cartItemDto.getTitle());
        cartItem.setPrice(cartItemDto.getPrice());
        cartItem.setCount(cartItemDto.getCount());
        cartItem.setSum(cartItemDto.getSum());
        return cartItem;
    }

}
