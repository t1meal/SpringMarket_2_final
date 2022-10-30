package ru.gb.market.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.entities.Role;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.repositories.RoleRepository;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findRoleByName (String roleName){
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role" + roleName + "is not found!"));
    }
}
