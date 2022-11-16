package ru.gb.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.core.services.OrderService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String userName) {
        orderService.createOrder(userName);
    }

}
