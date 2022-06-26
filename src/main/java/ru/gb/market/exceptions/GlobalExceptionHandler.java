package ru.gb.market.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    private ResponseEntity<?> catchResourceNotFoundException (ResourceNotFoundException e){
        return new ResponseEntity<>(new MarketError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<?> catchDataValidationException (DataValidationException e){
        return new ResponseEntity<>(new MarketError(e.getMessages()), HttpStatus.BAD_REQUEST);
    }
}
