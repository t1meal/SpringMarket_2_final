package ru.gb.market.products.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    List<ValidationFieldError> fields;

    public ValidationException(String message, List<ValidationFieldError> fields) {
        super(message);
        this.fields = fields;
    }
    //    private List<String> messages;
//    private final Date date;
//
//
//    public List<String> getMessages() {
//        return messages;
//    }
//
//    public void setMessages(List<String> messages) {
//        this.messages = messages;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public DataValidationException (List<String> messages){
//        this.messages = messages;
//        this.date = new Date();
//    }
}
