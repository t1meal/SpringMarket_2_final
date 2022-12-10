package ru.gb.market.api.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
@Schema(description = "Модель исключения")
public class MarketError {
    @Schema(description = "Статус код ошибки")
    String code;
    @Schema(description = "Описание ошибки")
    String message;
    @Schema(description = "Дата и время появления исключения")
        Date date;


    public MarketError(String message, String code) {
        this.message = message;
        this.code = code;
        this.date = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
