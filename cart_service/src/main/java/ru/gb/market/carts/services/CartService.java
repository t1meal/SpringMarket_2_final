package ru.gb.market.carts.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.api.dto.CartDto;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.carts.mappers.CartConverter;
import ru.gb.market.carts.models.CartItem;
import ru.gb.market.carts.models.Cart;
import ru.gb.market.carts.repositories.CartRepository;


import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Getter
public class CartService {
    private final CartRepository cartRepository;
    private final CartServiceUtils cartServiceUtils;

    private final CartConverter cartConverter;

    private Long UserID;
    private int totalPrice;

    public void createCart(Long id){
        Cart cart = new Cart(id);
        cartRepository.save(cart);
    }

    public CartDto getCurrentCart(Long userId) {
        Cart cart = cartServiceUtils.findCartById(userId);
        return cartConverter.entityToDto(cart);
    }

    public List<CartItem> findAllInCart(String userName) {
        UserID = cartServiceUtils.pullUserId(userName);
        if (cartRepository.findById(UserID).isEmpty()) {
            Cart cart = new Cart(UserID);
            totalPrice = 0;
            cartRepository.save(cart);
            return cart.getItems();
        }
        Cart cart = cartServiceUtils.findCartById(UserID);
        totalPrice = cart.getTotalPrice();
        return cart.getItems();
    }

    private void putToCart(Cart cart, ProductDto product) {
        cart.setNewItem(new CartItem(product)); //TODO: добавить в корзину метод для добавления продуктов вместо ред листа
        recalculate(cart);
        cartRepository.save(cart);
    }

    public void addProduct(String userName, ProductDto productDto) {
        Cart userCart = cartServiceUtils.findCartById(cartServiceUtils.pullUserId(userName));
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

    public void incDecCountOfItem(Long id, Integer marker) {
        Cart cart = cartServiceUtils.findCartById(UserID);
        CartItem item = cart.getItems().stream()
                .filter(cartItem -> id.equals(cartItem.getProductId()))
                .findAny()
                .get();
        item.changeQuantity(marker);
        recalculate(cart);

        if (item.getCount() < 1) {
            cart.getItems().removeIf(cartItem -> item.getProductId().equals(cartItem.getProductId()));
            recalculate(cart);
        }
        cartRepository.save(cart);
    }

    public void deleteItem(Long id) {
        Cart cart = cartServiceUtils.findCartById(UserID);
        cart.getItems().removeIf(cartItem -> cartItem.getProductId().equals(id));
        recalculate(cart);
        cartRepository.save(cart);
    }

    public void clearCart(Long id) {
        Cart cart = cartServiceUtils.findCartById(id);
        cart.getItems().clear();
        cart.setTotalPrice(0);
        cartRepository.save(cart);
    }

    private void recalculate(Cart cart) {
        totalPrice = 0;
        for (CartItem item : cart.getItems()) {
            totalPrice += item.getSum();
        }
        cart.setTotalPrice(totalPrice);
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }



}
