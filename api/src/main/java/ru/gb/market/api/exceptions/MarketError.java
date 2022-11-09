package ru.gb.market.api.exceptions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class MarketError {
    List<String> messages;
    Date date;

    public MarketError(List<String> messages) {
        this.messages = messages;
        this.date = new Date();
    }
    public MarketError(String message) {
//        this(List.of(message));
        this.messages.add(message);
    }
    public MarketError(String... messages) {
        this(Arrays.asList(messages));
    }


}
