package ru.gb.market.core.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.core.integrations.CartServiceIntegration;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin("*")

public class CartControllerIntegration {

    private final CartServiceIntegration cartServiceIntegration;

    @PostMapping("/cart")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductInCart(Principal principal, @RequestBody ProductDto productDto) {
        cartServiceIntegration.addProductToCart(principal.getName(), productDto);
    }
}
