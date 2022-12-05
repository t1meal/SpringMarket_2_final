package ru.gb.market.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.dto.OrderDto;
import ru.gb.market.core.entities.Order;
import ru.gb.market.core.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Методы работы с заказами")
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Запрос на создание нового заказа",
            responses = {
                    @ApiResponse(
                            description = "Заказ успешно создан", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = Order.class))
                    )
            }
    )
    @GetMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String userName) {
        orderService.createOrder(userName);
    }

    @Operation(
            summary = "Запрос на получение списка заказов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping("orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getUserOrders(@RequestHeader String userName) {
        return orderService.findByUserName(userName);
    }
}
