package ru.gb.market.products.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gb.market.api.exceptions.MarketError;
import ru.gb.market.api.exceptions.ResourceNotFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new MarketError(e.getMessage(), "404"), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    private ResponseEntity<ValidationError> catchDataValidationException(ValidationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ValidationError(e.getFields(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
