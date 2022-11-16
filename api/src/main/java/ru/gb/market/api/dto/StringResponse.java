package ru.gb.market.api.dto;

public class StringResponse {
    private String value;

    public StringResponse(){
    }

    public StringResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
