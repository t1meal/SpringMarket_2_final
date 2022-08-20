package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.mappers.ProductToDTOMapper;
import ru.gb.market.models.CartItemDto;
import ru.gb.market.models.ProductDto;
import ru.gb.market.services.CartService;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductToDTOMapper productMapper;

    @PostMapping("/products/cart")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductInCart(Principal principal, @RequestBody ProductDto productDto) {
        cartService.addProductInCart(productMapper.mapToProduct(productDto), principal.getName());
    }

    @GetMapping("/products/cart")
    @ResponseStatus(HttpStatus.OK)
    public List<CartItemDto> findAllInCart(Principal principal) {
        return cartService.findAllInCart(principal.getName());
    }

    @DeleteMapping("/products/cart/{title}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductFromCart(Principal principal, @PathVariable String title) {
        cartService.deleteItemFromCart(principal.getName(), title);
    }

    @PutMapping("/products/cart/inc/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void incCountOfItem(Principal principal, @PathVariable Integer id) {
        cartService.incDecCountOfItem(principal.getName(), id - 1, 1);
    }

    @PutMapping("/products/cart/dec/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void decCountOfItem(Principal principal, @PathVariable Integer id) {
        cartService.incDecCountOfItem(principal.getName(), id - 1, 0);
    }

    @GetMapping("/products/cart/sum")
    @ResponseStatus(HttpStatus.OK)
    public Integer pullSumOfOrder(Principal principal) {
        return cartService.getSumOfOrder(principal.getName());
    }
    @DeleteMapping("/products/cart")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllItems (Principal principal){
        cartService.deleteAllItems(principal.getName());
    }

}
