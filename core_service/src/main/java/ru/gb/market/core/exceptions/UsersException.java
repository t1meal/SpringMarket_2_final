package ru.gb.market.core.exceptions;

import java.sql.SQLException;

public class UsersException extends SQLException {

    public UsersException(String message) {
        super(message);
    }
}
