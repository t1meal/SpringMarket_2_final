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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public List<CartItem> findAllInCart(Principal principal) {
        return cartService.findAllInCart(principal.getName());
    }
    @PostMapping("/cart")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductInCart(Principal principal, @RequestBody ProductDto productDto) {
        cartService.addProduct(principal.getName() ,productDto);
    }

    @DeleteMapping("/cart/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductFromCart(@PathVariable Long id) {
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

    @GetMapping("/cart/sum")
    @ResponseStatus(HttpStatus.OK)
    public Integer pullSumOfOrder() {
        return cartService.getTotalPrice();
    }

    @DeleteMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllItems (){
        cartService.deleteAllItems();
    }

//    @GetMapping("/cart/empty")
//    @ResponseStatus(HttpStatus.OK)
//    public void checkEmptyCart(Principal principal) {
//         cartService.checkEmptyCart(principal.getName());
//    }

}
