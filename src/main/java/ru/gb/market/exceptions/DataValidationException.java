package ru.gb.market.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DataValidationException extends RuntimeException {
    private List<String> messages;
}
