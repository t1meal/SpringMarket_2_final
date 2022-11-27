package ru.gb.market.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.carts.properties.IntegrationProperties;


@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final WebClient webClient;
    private final IntegrationProperties integrationProperties;

    public ProductDto getProductById(Long id) {
        return webClient.get()
                .uri(integrationProperties.getProductsUrl() + "/products/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Product with id" + id + "is not found!"))
                )
                .bodyToMono(ProductDto.class)
                .block();
    }

}
