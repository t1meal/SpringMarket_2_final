package ru.gb.market.api.exceptions;

import java.util.Date;
import java.util.List;

public class DataValidationException extends RuntimeException {
    private List<String> messages;
    private final Date date;


    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Date getDate() {
        return date;
    }

    public DataValidationException (List<String> messages){
        this.messages = messages;
        this.date = new Date();
    }
}
