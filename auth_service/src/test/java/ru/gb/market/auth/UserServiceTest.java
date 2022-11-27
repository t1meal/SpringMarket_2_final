package ru.gb.market.auth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.market.auth.entities.UserEntity;
import ru.gb.market.auth.repositories.UserRepository;
import ru.gb.market.auth.services.UserService;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findOneUserTest() {
        UserEntity user = new UserEntity();
        user.setUsername("John");
        user.setEmail("john@mail.ru");
        // ? Mockito.when().thenReturn();

        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findByUsername("John");

        UserEntity userJohn = userService.findByUsername("John").get();

        Assertions.assertNotNull(userJohn);
        Assertions.assertEquals("john@mail.ru", userJohn.getEmail());

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("John"));
    }
}
