package ru.gb.market.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.market.api.dto.CartDto;
import ru.gb.market.api.dto.CartItemDto;

import ru.gb.market.api.dto.CategoryDto;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.core.entities.Order;
import ru.gb.market.core.entities.OrderItem;

import ru.gb.market.core.integrations.CartServiceIntegration;
import ru.gb.market.core.integrations.ProductServiceIntegration;
import ru.gb.market.core.mappers.CartItemDtoMapper;
import ru.gb.market.core.repositories.OrderRepository;
import ru.gb.market.core.services.OrderService;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartItemDtoMapper cartItemDtoMapper;

    @MockBean
    private CartServiceIntegration cartServiceIntegration;
    @MockBean
    private ProductServiceIntegration productService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void orderServiceTest (){ // TODO проверить данные для теста!
        CartDto cartDto = new CartDto();
        CartItemDto cartItemDto = new CartItemDto();
        cartDto.setId(1L);
        cartItemDto.setProductId(512L);
        cartItemDto.setTitle("bread");
        cartItemDto.setPrice(60);
        cartItemDto.setCount(2);
        cartItemDto.setSum(120);
        cartDto.setTotalPrice(120);
        cartDto.setItems(List.of(cartItemDto));

        Mockito.doReturn(cartDto).when(cartServiceIntegration).getUserCart("Bob");

        ProductDto product = new ProductDto();

        product.setId(512L);
        product.setTitle("bread");
        product.setPrice(60);
        product.setCategoryTitle("Food");

        Mockito.doReturn(product).when(productService).getProductById(512L);

        Order order = orderService.createOrder("Bob");
        Assertions.assertEquals(order.getTotalPrice(), 120);

        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void cartItemDtoMapperTest (){
        List <CartItemDto> cartItems = new ArrayList<>();
        CartItemDto firstItem = new CartItemDto(1L, "water", 30, 2, 60);
        CartItemDto secondItem = new CartItemDto(2L, "cheese", 100, 1, 100);
        cartItems.add(firstItem);
        cartItems.add(secondItem);
        int totalPrice = 160;

        Order order = new Order();
        order.setId(200L);
        order.setTotalPrice(totalPrice);
        order.setUsername("Bob");

        List <OrderItem> orderItems = cartItemDtoMapper.mapToOrderItems(cartItems, order);

        Assertions.assertEquals(orderItems.get(0).getSum(), firstItem.getSum());
        Assertions.assertEquals(orderItems.get(1).getOrder(), order);

    }

}
