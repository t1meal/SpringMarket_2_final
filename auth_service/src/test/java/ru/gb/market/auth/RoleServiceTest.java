package ru.gb.market.auth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.market.auth.entities.RoleEntity;
import ru.gb.market.auth.repositories.RoleRepository;
import ru.gb.market.auth.services.RoleService;

import java.util.Optional;

@SpringBootTest
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void findOneUserTest() {
        RoleEntity role = new RoleEntity("ROLE_USER");

        Mockito.doReturn(Optional.of(role))
                .when(roleRepository)
                .findByName("ROLE_USER");

        RoleEntity userRole = roleService.findRoleByName("ROLE_USER");

        Assertions.assertNotNull(userRole);
        Assertions.assertEquals("ROLE_USER", userRole.getName());

        Mockito.verify(roleRepository, Mockito.times(1)).findByName(ArgumentMatchers.eq("ROLE_USER"));
    }
}
