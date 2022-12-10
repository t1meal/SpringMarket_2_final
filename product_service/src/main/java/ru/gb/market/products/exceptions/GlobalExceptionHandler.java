package ru.gb.market.products.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gb.market.api.exceptions.DataValidationException;
import ru.gb.market.api.exceptions.MarketError;
import ru.gb.market.api.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new MarketError(e.getMessage(), "404"), HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler
    private ResponseEntity<?> catchDataValidationException(DataValidationException e) {
        return new ResponseEntity<>(new MarketError(e.getMessage(), "400"), HttpStatus.BAD_REQUEST);
    }

}