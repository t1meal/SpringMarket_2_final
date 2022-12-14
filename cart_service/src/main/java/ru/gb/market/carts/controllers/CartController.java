package ru.gb.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.dto.CartDto;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.carts.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor

public class CartController {

    private final CartService cartService;

    @PostMapping("/guestCart")
    @ResponseStatus(HttpStatus.OK)
    public void getGuestCart(@RequestHeader Long userID, @RequestBody CartDto guestCart){
        cartService.mergeCarts(userID, guestCart);
    }

    @PutMapping ("/create/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public void newCart (@PathVariable Long userID){
        cartService.createCart(userID);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CartDto getCurrentCart(@RequestHeader Long userID) {
        return cartService.getCurrentCart(userID);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductInCart(@RequestHeader Long userID, @RequestBody ProductDto productDto) {
        cartService.addProductToCart(userID, productDto);
    }

    @PutMapping("/item/inc/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CartDto incCountOfItem(@RequestHeader Long userID, @PathVariable Long id) {
        return cartService.changeQuantityOfItem(userID, id, 1);
    }

    @PutMapping("/item/dec/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CartDto decCountOfItem(@RequestHeader Long userID, @PathVariable Long id) {
        return cartService.changeQuantityOfItem(userID, id, -1);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void clearCart(@RequestHeader Long userID){
        cartService.clearCart(userID);
    }
    @DeleteMapping("/item/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItemFromCart(@RequestHeader Long userID, @PathVariable Long id) {
        cartService.deleteItem(userID, id);
    }

}
