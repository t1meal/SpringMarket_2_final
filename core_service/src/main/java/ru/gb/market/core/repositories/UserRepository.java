package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.core.entities.UserEntity;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername (String username);
}
