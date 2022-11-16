package ru.gb.market.auth.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.auth.entities.RoleEntity;
import ru.gb.market.auth.repositories.RoleRepository;


@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleEntity findRoleByName (String roleName){
        return roleRepository.findByName(roleName).orElseThrow(
                () -> new ResourceNotFoundException("Role" + roleName + "is not found!"));
    }
}
