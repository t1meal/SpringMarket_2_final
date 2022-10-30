package ru.gb.market.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.entities.Role;
import ru.gb.market.services.RoleService;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class RoleGenerator {

    private final RoleService roleService;

    public List<Role> addOneRole (String role){
        List<Role> roles = new ArrayList<>();
        Role userRole = roleService.findRoleByName(role);
        roles.add(userRole);
        return roles;
    }
}
