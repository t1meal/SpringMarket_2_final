package ru.gb.market.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.market.api.dto.UserDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.carts.properties.IntegrationProperties;


@Component
@RequiredArgsConstructor
public class UserServiceIntegration {

    private final WebClient webClient;
    private final IntegrationProperties integrationProperties;

    public UserDto getUserByUserName(String userName) {
        return webClient.get()
                .uri(integrationProperties.getAuthUrl() + "/user")
                .header("userName", userName)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("User with username " + userName + " is not found!"))
                )
                .bodyToMono(UserDto.class)
                .block();
    }

}
