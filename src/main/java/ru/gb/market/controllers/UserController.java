package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.UserDto;
import ru.gb.market.exceptions.DataValidationException;
import ru.gb.market.model.User;
import ru.gb.market.services.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping("/new_user")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        userService.saveUser(new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail()));
    }
}
