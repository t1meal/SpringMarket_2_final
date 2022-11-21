package ru.gb.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.market.api.dto.CartDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.core.properties.CartServiceIntegrationProperties;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;
    private final CartServiceIntegrationProperties cartServiceProperties;

    public CartDto getUserCart(String userName) {
        return cartServiceWebClient.get()
                .uri(cartServiceProperties.getCartUrl() + "/cart")
                .header("userName", userName)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Корзина не найдена"))
                )
                .bodyToMono(CartDto.class)
                .block();
    }
}
