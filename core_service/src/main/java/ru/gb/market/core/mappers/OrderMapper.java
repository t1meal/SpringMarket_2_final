package ru.gb.market.core.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.OrderDto;
import ru.gb.market.core.entities.Order;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUsername(orderDto.getUsername());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setUser_email(order.getUser_email());
        orderDto.setItems(order.getItems().stream()
                .map(orderItemMapper::entityToDto)
                .collect(Collectors.toList()));
        return orderDto;
    }

}
