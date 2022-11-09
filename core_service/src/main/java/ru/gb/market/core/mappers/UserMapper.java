package ru.gb.market.core.mappers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.UserDto;
import ru.gb.market.core.entities.UserEntity;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserDto entityToDto (UserEntity user){ // TODO замапить роли
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public UserEntity dtoToEntity (UserDto userDto){
        UserEntity user = new UserEntity();
        return user;
    }
}
