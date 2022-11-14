package ru.gb.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.market.api.dto.CartDto;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;


import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWeClient;

//    public Optional<CartDto> getCartByUserId (Long id){
//        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8090/market-carts/api/v1/cart/" + id, CartDto.class));
//
//    }
//    public void clearCart (Long id){
//        restTemplate.delete("http://localhost:8090/market-carts/api/v1/cart/" + id);
//    }
//
//    public void createCart (Long id){
//        restTemplate.put("http://localhost:8090/market-carts/api/v1/cart/create/" + id, Object.class);
//    }

    public CartDto getUserCart(Long userId) {
        CartDto cartDto = cartServiceWeClient.get()
                .uri("http://localhost:8090/market-carts/api/v1/cart/" + userId)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Корзина не найдена"))
                )
                .bodyToMono(CartDto.class)
                .block();
    }

    public void addProductToCart(String userName, ProductDto productDto) {

    }

    public void clearUserCart(Long userId) {
        cartServiceWeClient.delete()
                .uri("http://localhost:8090/market-carts/api/v1/cart/")
                .header("userId", userId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }


}
