package ru.gb.market.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.entities.ProductEntity;
import ru.gb.market.mappers.ProductConverter;
import ru.gb.market.models.ShoppingCart;
import ru.gb.market.models.CartItem;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.repositories.CartRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
public class CartService {
    private final CartRepository cartRepository;
    private final CartServiceUtils cartServiceUtils;
    private final ProductConverter productConverter;

    private Long UserID;
    private int totalPrice;

    public List<CartItem> findAllInCart(String userName) {
        UserID = cartServiceUtils.pullUserId(userName);
        if (cartRepository.findById(UserID).isEmpty()) {
            ShoppingCart cart = new ShoppingCart(UserID);
            totalPrice = 0;
            cartRepository.save(cart);
            return cart.getItems();
        }
        ShoppingCart cart = cartServiceUtils.findCartById(UserID);
        totalPrice = cart.getTotalPrice();
        return cart.getItems();
    }

//    public void checkEmptyCart(String name) {
//
//    }

    private void putToCart(ShoppingCart cart, ProductEntity product) {
        cart.setNewItem(new CartItem(product)); //TODO: добавить в корзину метод для добавления продуктов вместо ред листа
        recalculate(cart);
        cartRepository.save(cart);
    }

    public void addProduct(String userName, ProductDto productDto) {
        ProductEntity product = productConverter.dtoToEntity(productDto);
        ShoppingCart userCart = cartServiceUtils.findCartById(cartServiceUtils.pullUserId(userName));
        if (addProductIfExist(product.getId(), userCart)) {
            return;
        }
        putToCart(userCart, product);
    }

    private boolean addProductIfExist(Long productId, ShoppingCart cart) {
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
        ShoppingCart cart = cartServiceUtils.findCartById(UserID);
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
        ShoppingCart cart = cartServiceUtils.findCartById(UserID);
        cart.getItems().removeIf(cartItem -> cartItem.getProductId().equals(id));
        recalculate(cart);
        cartRepository.save(cart);
    }

    public void deleteAllItems() {
        ShoppingCart cart = cartServiceUtils.findCartById(UserID);
        cart.getItems().clear();
        cart.setTotalPrice(0);
        cartRepository.save(cart);
    }

    private void recalculate(ShoppingCart cart) {
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
