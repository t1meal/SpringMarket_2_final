package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername (String username);
}
