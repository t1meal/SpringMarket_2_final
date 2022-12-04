package ru.gb.market.carts.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.CartItemDto;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.carts.integrations.ProductServiceIntegration;
import ru.gb.market.carts.models.CartItem;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CartItemConverter {
    private final ProductServiceIntegration productService;

    public CartItemDto entityToDto (CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setTitle(cartItem.getTitle());
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setQuantity(cartItem.getCount());
        cartItemDto.setSum(cartItem.getSum());
        return cartItemDto;
    }
    public CartItem dtoToEntity (CartItemDto cartItemDto){
        CartItem cartItem = new CartItem();
        ProductDto productDto = productService.getProductById(cartItemDto.getProductId());
        cartItem.setProductId(productDto.getId());
        cartItem.setTitle(productDto.getTitle());
        cartItem.setPrice(productDto.getPrice());
        cartItem.setCount(cartItemDto.getQuantity());
        cartItem.setSum(calculateSum(cartItem));
        return cartItem;
    }

    public BigDecimal calculateSum(CartItem cartItem){
        BigDecimal totalSum = BigDecimal.ZERO;

        BigDecimal itemCost = cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount()));
        totalSum = totalSum.add(itemCost);
        return totalSum;
    }

}
