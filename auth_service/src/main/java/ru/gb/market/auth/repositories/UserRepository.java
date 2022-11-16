package ru.gb.market.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.auth.entities.UserEntity;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername (String username);
}
