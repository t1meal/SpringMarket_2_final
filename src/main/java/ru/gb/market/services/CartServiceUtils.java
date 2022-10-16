package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.models.ShoppingCart;
import ru.gb.market.repositories.CartRepository;


@Service
@RequiredArgsConstructor
public class CartServiceUtils {

    private final CartRepository repository;
    private final UserService userService;

    public ShoppingCart findCartById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart for user with id " + id + " not found!"));
    }
    public Long pullUserId(String userName) {
        return userService.findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User with name" + userName + "not found!"))
                .getId();
    }
}
