package ru.gb.market.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.carts.integrations.UserServiceIntegration;
import ru.gb.market.carts.models.Cart;
import ru.gb.market.carts.repositories.CartRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartServiceUtils {

    private final CartRepository repository;
    private final UserServiceIntegration userServiceIntegration;

    public Cart findCartById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart for user with id " + id + " not found!"));
    }

    public Optional<Cart> findCartByIdUtil(Long id) {
        return repository.findById(id);
    }

    public Long pullUserId(String userName) {
        return userServiceIntegration.getUserByUserName(userName).getId();
    }
}
