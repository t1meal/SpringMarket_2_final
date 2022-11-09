package ru.gb.market.core.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.core.entities.Role;
import ru.gb.market.core.repositories.RoleRepository;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findRoleByName (String roleName){
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role" + roleName + "is not found!"));
    }
}
