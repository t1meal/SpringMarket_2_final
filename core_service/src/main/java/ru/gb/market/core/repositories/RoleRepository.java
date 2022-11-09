package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.core.entities.Role;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName (String roleName);
}
