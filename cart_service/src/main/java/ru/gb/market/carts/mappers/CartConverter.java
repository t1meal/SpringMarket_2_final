package ru.gb.market.carts.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.CartDto;
import ru.gb.market.carts.models.Cart;
import ru.gb.market.carts.models.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setTotalPrice(cart.getTotalPrice());

        if (cart.getItems() == null){
            cartDto.setItems(new ArrayList<>());
            return cartDto;
        }

        List <CartItem> modifyList = new ArrayList<>(cart.getItems());
        cartDto.setItems
                (modifyList.stream()
                        .map(cartItemConverter::entityToDto)
                        .collect(Collectors.toList()));
        return cartDto;
    }

    public Cart dtoToEntity(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setTotalPrice(cartDto.getTotalPrice());
        cart.setItems(cartDto.getItems().stream()
                .map(cartItemConverter::dtoToEntity)
                .collect(Collectors.toList()));
        return cart;
    }
}
