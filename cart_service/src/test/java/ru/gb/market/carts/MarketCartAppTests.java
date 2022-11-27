package ru.gb.market.carts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.api.dto.UserDto;
import ru.gb.market.carts.integrations.CoreServiceIntegration;
import ru.gb.market.carts.integrations.UserServiceIntegration;
import ru.gb.market.carts.services.CartService;

@SpringBootTest
class MarketCartAppTests {


    @Autowired
    private CartService cartService;

    @MockBean
    private CoreServiceIntegration coreServiceIntegration;

    @MockBean
    private UserServiceIntegration userServiceIntegration;

    @BeforeEach
    public void initCart (){ cartService.clearCart("user");}

    @Test

    void addToCartTest() {
        UserDto user = new UserDto();
        user.setId(1L);
        user.setUsername("user");
        ProductDto product5 = new ProductDto(5L, "bread", 50, "Food");
        ProductDto product7 = new ProductDto(7L, "bread", 50, "Food");

        Mockito.doReturn(product5).when(coreServiceIntegration).getProductById(5L);
        Mockito.doReturn(product7).when(coreServiceIntegration).getProductById(7L);
        Mockito.doReturn(user).when(userServiceIntegration).getUserByUserName("user");

        ProductDto firthProduct = coreServiceIntegration.getProductById(5L);
        ProductDto sevenProduct = coreServiceIntegration.getProductById(7L);

        cartService.addProductToCart("user", firthProduct);
        cartService.addProductToCart("user", sevenProduct);


        Mockito.verify(coreServiceIntegration, Mockito.times(1)).getProductById(ArgumentMatchers.eq(5L));
        Assertions.assertEquals(3, cartService.getCurrentCart("user").getItems().size());
    }
}
