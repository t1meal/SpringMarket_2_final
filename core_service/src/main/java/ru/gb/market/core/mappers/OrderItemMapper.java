package ru.gb.market.core.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.OrderItemDto;
import ru.gb.market.core.entities.OrderItem;


@Component
@AllArgsConstructor
public class OrderItemMapper {

    public OrderItemDto entityToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrderId(orderItem.getOrder().getId());
        orderItemDto.setProductTitle(orderItem.getTitle());
        orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
        orderItemDto.setCount(orderItem.getCount());
        orderItemDto.setSum(orderItem.getSum());
        return orderItemDto;
    }

}
