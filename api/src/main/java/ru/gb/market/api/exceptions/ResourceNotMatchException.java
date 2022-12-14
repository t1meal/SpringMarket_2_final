package ru.gb.market.api.exceptions;

public class ResourceNotMatchException extends RuntimeException {

    public ResourceNotMatchException(String message) {
        super(message);
    }
}
