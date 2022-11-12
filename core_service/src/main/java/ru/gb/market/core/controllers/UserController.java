package ru.gb.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.dto.NewUserDto;
import ru.gb.market.api.dto.UserDto;
import ru.gb.market.core.entities.UserEntity;
import ru.gb.market.core.mappers.UserMapper;
import ru.gb.market.core.services.UserService;
import ru.gb.market.core.utils.BindingResultService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin("*")

public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    @PostMapping("/new_user")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody @Validated NewUserDto userDto, BindingResult bindingResult) {
        BindingResultService.checkError(bindingResult);
        userService.createUser(userDto);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserByUsername (String name){
       return userMapper.entityToDto(userService.findByUsername(name).get());
    }
}
