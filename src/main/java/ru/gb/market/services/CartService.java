package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.entities.Product;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.models.Cart;
import ru.gb.market.models.CartItem;
import ru.gb.market.models.CartItemDto;
import ru.gb.market.repositories.CartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserService userService;

    public List<CartItemDto> findAllInCart(String userName) {
        if (cartRepository.findById(pullUserId(userName)).isEmpty()) {
            Cart cart = new Cart();
            cart.setId(pullUserId(userName));
            cart.setCartOwner(userName);
            cartRepository.save(cart);
            return new ArrayList<>();
        }
        List<CartItem> list = findCartById(pullUserId(userName)).getItems();
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        list.forEach(cartItem ->
                cartItemDtoList.add(CartItemDto.builder()
                        .id(list.indexOf(cartItem) + 1)
                        .title(cartItem.getTitle())
                        .count(cartItem.getCount())
                        .price(cartItem.getPrice())
                        .sum(cartItem.getSum())
                        .build()));
        return cartItemDtoList;
    }

    public void addProductInCart(Product product, String userName) {
        Cart userCart = findCartById(pullUserId(userName));
        putToCart(userCart, product);
    }

    private void putToCart(Cart cart, Product product) {
        if (cart.getItems().stream().anyMatch(cartItem -> product.getTitle().equals(cartItem.getTitle()))) {
            cartRepository.save(increaseCountAndSum(cart, product));
            return;
        }
        CartItem cartItem = CartItem.builder()
                .title(product.getTitle())
                .count(1)
                .price(product.getPrice())
                .sum(product.getPrice())
                .build();
        cart.getItems().add(cartItem);
        cartRepository.save(cart);
    }

    public void deleteItemFromCart(String userName, String productTitle) {
        Cart cart = findCartById(pullUserId(userName));
        cart.getItems().removeIf(cartItem -> cartItem.getTitle().equals(productTitle));
        cartRepository.save(cart);
    }

    public void incDecCountOfItem(String userName, Integer id, Integer marker) {
        Cart cart = findCartById(pullUserId(userName));
        CartItem item = cart.getItems().get(id);
        if (marker == 1) {
            item.setCount(item.getCount() + 1);
        } else if (marker == 0) {
            item.setCount(item.getCount() - 1);
        }
        item.setSum(item.getCount() * item.getPrice());
        cart.getItems().set(id, item);
        cartRepository.save(cart);
    }
    public void deleteAllItems(String userName) {
        Cart cart = findCartById(pullUserId(userName));
        cart.setItems(new ArrayList<>());
        cartRepository.save(cart);
    }
    ////////////
    private Cart findCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart for user with id " + id + " not found!"));
    }

    private Long pullUserId(String userName) {
        return userService.findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User with name" + userName + "not found!"))
                .getId();
    }

    private Cart increaseCountAndSum(Cart cart, Product product) {
        for (CartItem element : cart.getItems()) {
            if (element.getTitle().equals(product.getTitle())) {
                element.setCount(element.getCount() + 1);
                element.setSum(element.getPrice() * element.getCount());
                break;
            }
        }
        return cart;
    }

    public Integer getSumOfOrder(String userName) {
        Optional<Cart> optionalCart = cartRepository.findById(pullUserId(userName));
        if (optionalCart.isEmpty()){
            return 0;
        }

        Cart cart = optionalCart.get();
        if (cart.getItems().isEmpty()){
            return 0;
        }
        Integer totalSum = 0;
        for (CartItem item : cart.getItems()) {
            totalSum += item.getSum();
        }
        return totalSum;
    }


}
