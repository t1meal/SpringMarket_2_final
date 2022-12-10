package ru.gb.market.api.exceptions;

import java.sql.SQLException;

public class UsersException extends SQLException {

    public UsersException(String message) {
        super(message);
    }
}
