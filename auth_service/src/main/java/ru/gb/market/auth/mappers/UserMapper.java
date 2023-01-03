package ru.gb.market.auth.mappers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.UserDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.auth.entities.RoleEntity;
import ru.gb.market.auth.entities.UserEntity;


@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDto entityToDto(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        RoleEntity role = user.getRoles().stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Role fo user " + user.getUsername() + " is not found!"));
        userDto.setRole(role.getName());
        return userDto;
    }
}
