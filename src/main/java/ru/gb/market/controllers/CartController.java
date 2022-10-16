package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.models.CartItem;
import ru.gb.market.services.CartService;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/products/cart")
    @ResponseStatus(HttpStatus.OK)
    public List<CartItem> findAllInCart(Principal principal) {
        return cartService.findAllInCart(principal.getName());
    }
    @PostMapping("/products/cart")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductInCart(Principal principal, @RequestBody ProductDto productDto) {
        cartService.addProductIfExist(principal.getName() ,productDto);
    }

    @DeleteMapping("/products/cart/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductFromCart(@PathVariable Long id) {
        cartService.deleteItem(id);
    }

    @PutMapping("/products/cart/inc/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void incCountOfItem(@PathVariable Long id) {
        cartService.incDecCountOfItem(id, 1);
    }

    @PutMapping("/products/cart/dec/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void decCountOfItem(@PathVariable Long id) {
        cartService.incDecCountOfItem( id, -1);
    }

    @GetMapping("/products/cart/sum")
    @ResponseStatus(HttpStatus.OK)
    public Integer pullSumOfOrder() {
        return cartService.getTotalPrice();
    }

    @DeleteMapping("/products/cart")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllItems (){
        cartService.deleteAllItems();
    }

}
