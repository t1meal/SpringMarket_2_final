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
    public void getGuestCart(@RequestHeader String userName, @RequestBody CartDto guestCart){
        cartService.mergeCarts(userName, guestCart);
    }

    @PutMapping ("/create/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void newCart (@PathVariable Long id){
        cartService.createCart(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CartDto getCurrentCart(@RequestHeader String userName) {
        return cartService.getCurrentCart(userName);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductInCart(@RequestHeader String userName, @RequestBody ProductDto productDto) {
        cartService.addProductToCart(userName, productDto);
    }

    @PutMapping("/item/inc/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CartDto incCountOfItem(@RequestHeader String userName, @PathVariable Long id) {
        return cartService.changeQuantityOfItem(userName, id, 1);
    }

    @PutMapping("/item/dec/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CartDto decCountOfItem(@RequestHeader String userName, @PathVariable Long id) {
        return cartService.changeQuantityOfItem(userName, id, -1);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void clearCart(@RequestHeader String userName){
        cartService.clearCart(userName);
    }
    @DeleteMapping("/item/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItemFromCart(@RequestHeader String userName, @PathVariable Long id) {
        cartService.deleteItem(userName, id);
    }

}
