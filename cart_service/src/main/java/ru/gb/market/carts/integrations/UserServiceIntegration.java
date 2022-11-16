package ru.gb.market.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.market.api.dto.UserDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<UserDto> getUserByUsername (String name){
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8080/market/api/v1/users" + name, UserDto.class));
    }

}
