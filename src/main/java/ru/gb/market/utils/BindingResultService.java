package ru.gb.market.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import ru.gb.market.exceptions.DataValidationException;

import java.util.stream.Collectors;

public class BindingResultService {
    public static void checkError (BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
    }
}
