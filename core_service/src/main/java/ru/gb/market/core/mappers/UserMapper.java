package ru.gb.market.core.mappers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.NewUserDto;
import ru.gb.market.api.dto.UserDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.core.entities.Role;
import ru.gb.market.core.entities.UserEntity;
import ru.gb.market.core.services.RoleService;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleService roleService;

    public UserDto entityToDto(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        Role role = user.getRoles().stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Role fo user " + user.getUsername() + " is not found!"));
        userDto.setRole(role.getName());
        return userDto;
    }

    public UserEntity dtoToEntity(NewUserDto userDto) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setEmail(userDto.getEmail());
        return newUser;
    }
}
