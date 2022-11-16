package ru.gb.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.dto.CartDto;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.carts.services.CartService;

import java.security.Principal;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class CartController {

    private final CartService cartService;

    @PutMapping ("/cart/create/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void newCart (@PathVariable Long id){
        cartService.createCart(id);
    }

    @GetMapping("/cart/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CartDto getCurrentCart(@PathVariable Long id) {
        return cartService.getCurrentCart(id);
    }

    @PostMapping("/cart")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductInCart(Principal principal, @RequestBody ProductDto productDto) {
        cartService.addProduct(principal.getName(), productDto);
    }

    @DeleteMapping("/cart/item/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItemFromCart(@PathVariable Long id) {
        cartService.deleteItem(id);
    }

    @PutMapping("/cart/inc/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void incCountOfItem(@PathVariable Long id) {
        cartService.incDecCountOfItem(id, 1);
    }

    @PutMapping("/cart/dec/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void decCountOfItem(@PathVariable Long id) {
        cartService.incDecCountOfItem( id, -1);
    }

    @DeleteMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public void clearCart(Long id){
        cartService.clearCart(id);
    }

//    @GetMapping("/cart/sum")
//    @ResponseStatus(HttpStatus.OK)
//    public Integer pullSumOfOrder() {
//        return cartService.getTotalPrice();
//    }

}
