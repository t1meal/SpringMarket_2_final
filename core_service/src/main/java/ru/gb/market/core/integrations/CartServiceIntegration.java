package ru.gb.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.market.api.dto.CartDto;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<CartDto> getCartById (Long id){
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8090/market/api/v1/cart" + id, CartDto.class));
    }
    public void clearCart (Long id){
         restTemplate.delete("http://localhost:8090/market/api/v1/cart" + id);
    }

}
