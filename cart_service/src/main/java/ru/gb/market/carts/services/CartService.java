package ru.gb.market.carts.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.gb.market.api.dto.CartDto;
import ru.gb.market.api.dto.CartItemDto;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.carts.integrations.ProductServiceIntegration;
import ru.gb.market.carts.mappers.CartConverter;
import ru.gb.market.carts.mappers.CartItemConverter;
import ru.gb.market.carts.models.CartItem;
import ru.gb.market.carts.models.Cart;
import ru.gb.market.carts.repositories.CartRepository;

import java.math.BigDecimal;
import java.util.*;


@Service
@RequiredArgsConstructor
@Getter
public class CartService {
    private final CartRepository cartRepository;
    private final CartServiceUtils cartServiceUtils;
    private final CartConverter cartConverter;
    private final ProductServiceIntegration productServiceIntegration;
    private final CartItemConverter cartItemConverter;

    public void createCart(Long id) {
        Cart cart = new Cart(id);
        cartRepository.save(cart);
    }

    public CartDto getCurrentCart(String userName) {
        Long userId = cartServiceUtils.pullUserId(userName);
        if (cartServiceUtils.findCartByIdUtil(userId).isEmpty()) {
            createCart(userId);
        }
        Cart cart = cartServiceUtils.findCartById(userId);
        return cartConverter.entityToDto(cart);
    }

    private void putToCart(Cart cart, ProductDto product) {
        cart.setNewItem(new CartItem(product));
        recalculate(cart);
        cartRepository.save(cart);
    }

    public void addProductToCart(String userName, ProductDto productDto) {
        Long userId = cartServiceUtils.pullUserId(userName);
        Optional<Cart> cart = cartServiceUtils.findCartByIdUtil(userId);
        if (cart.isEmpty()) {
            createCart(userId);
        }
        Cart userCart = cartServiceUtils.findCartById(userId);
        if (addProductIfExist(productDto.getId(), userCart)) {
            return;
        }
        putToCart(userCart, productDto);
    }

    private boolean addProductIfExist(Long productId, Cart cart) {
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
            return false;
        }
        for (CartItem item : cart.getItems()) {
            if (item.getProductId().equals(productId)) {
                item.changeQuantity(1);
                recalculate(cart);
                cartRepository.save(cart);
                return true;
            }
        }
        return false;
    }

    public CartDto changeQuantityOfItem(String userName, Long id, Integer marker) {
        Cart cart = cartServiceUtils.findCartById(cartServiceUtils.pullUserId(userName));
        CartItem item = cart.getItems().stream()
                .filter(cartItem -> id.equals(cartItem.getProductId()))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("This product does not exist!"));
        item.changeQuantity(marker);
        recalculate(cart);

        if (item.getCount() < 1) {
            cart.getItems().removeIf(cartItem -> item.getProductId().equals(cartItem.getProductId()));
            recalculate(cart);
        }
        cartRepository.save(cart);
        return cartConverter.entityToDto(cart);
    }

    public void deleteItem(String userName, Long productId) {
        Long userId = cartServiceUtils.pullUserId(userName);
        Cart cart = cartServiceUtils.findCartById(userId);
        List<CartItem> modifyList = new ArrayList<>(cart.getItems());
        modifyList.removeIf(cartItem -> cartItem.getProductId().equals(productId));
        cart.setItems(modifyList);
        recalculate(cart);
        cartRepository.save(cart);
    }

    public void clearCart(String userName) {
        Cart cart = cartServiceUtils.findCartById(cartServiceUtils.pullUserId(userName));
        List<CartItem> emptyList = new ArrayList<>();
        cart.setItems(emptyList);
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    private void recalculate(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem item : cart.getItems()) {
            totalPrice = totalPrice.add(item.getSum());
        }
        cart.setTotalPrice(totalPrice);
    }

    public void mergeCarts(String userName, CartDto guestCart) {
        Cart cart = cartServiceUtils.findCartById(cartServiceUtils.pullUserId(userName));

        for (CartItemDto guestItem: guestCart.getItems()) {
            CartItem guestCartItem = cartItemConverter.dtoToEntity(guestItem);
            CartItem userCartItem = cart.getItems().stream().filter(item -> item.getProductId().equals(guestCartItem.getProductId())).findFirst().orElse(null);
            if (userCartItem != null){
                userCartItem.setCount(userCartItem.getCount() + guestCartItem.getCount());
                userCartItem.setSum(cartItemConverter.calculateSum(userCartItem));
            } else {
                cart.getItems().add(guestCartItem);
            }
        }
        recalculate(cart);
        cartRepository.save(cart);
    }
}
