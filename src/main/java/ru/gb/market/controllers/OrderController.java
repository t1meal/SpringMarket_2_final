package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.services.OrderService;
import java.security.Principal;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal) {
        orderService.createOrder(principal.getName());
    }

}
