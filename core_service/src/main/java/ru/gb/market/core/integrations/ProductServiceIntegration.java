package ru.gb.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.core.properties.IntegrationProperties;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final WebClient cartServiceWebClient;
    private final IntegrationProperties integrationProperties;

    public ProductDto getProductById(Long productId) {
        return cartServiceWebClient.get()
                .uri(integrationProperties.getProductsUrl() + "/products/" + productId)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Product is not found!"))
                )
                .bodyToMono(ProductDto.class)
                .block();
    }

    public List<ProductDto> getAllProducts() {
        return cartServiceWebClient.get()
                .uri(integrationProperties.getProductsUrl() + "/products_")
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Products is not found!"))
                )
                .bodyToMono(new ParameterizedTypeReference<List<ProductDto>>() {   })
                .block();
    }

}
