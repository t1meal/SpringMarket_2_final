package ru.gb.market.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.auth.entities.RoleEntity;


import java.util.Optional;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName (String roleName);
}
