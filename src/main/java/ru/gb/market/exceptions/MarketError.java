package ru.gb.market.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MarketError {
    String message;
    Date date;

    public MarketError(String message) {
        this.message = message;
        this.date = new Date();
    }
}
