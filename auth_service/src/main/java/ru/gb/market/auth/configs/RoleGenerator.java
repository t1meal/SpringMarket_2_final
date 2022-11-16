package ru.gb.market.auth.configs;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.auth.entities.RoleEntity;
import ru.gb.market.auth.services.RoleService;


import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class RoleGenerator {

    private final RoleService roleService;

    public List<RoleEntity> addOneRole (String role){
        List<RoleEntity> roles = new ArrayList<>();
        RoleEntity userRole = roleService.findRoleByName(role);
        roles.add(userRole);
        return roles;
    }
}
