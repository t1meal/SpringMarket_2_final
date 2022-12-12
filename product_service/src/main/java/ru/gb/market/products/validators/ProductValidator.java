package ru.gb.market.products.validators;

import org.springframework.stereotype.Component;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.products.exceptions.ValidationException;
import ru.gb.market.products.exceptions.ValidationFieldError;

import java.util.ArrayList;
import java.util.List;
@Component
public class ProductValidator implements Validator<ProductDto> {

    @Override
    public void validate(ProductDto p) {
        List<ValidationFieldError> errors = new ArrayList<>();
        if (p.getPrice().doubleValue() < 0.01) {
            errors.add(new ValidationFieldError("price", p.getPrice().toString(), "Цена не может быть меньше 1 коп!"));
        }
        if (p.getTitle().length() < 3 || p.getTitle().length() > 255) {
            errors.add(new ValidationFieldError("title", p.getTitle(), "Длина названия товара должна находится в пределах 3..255 символов!"));
        }
        if (errors.isEmpty()){
            throw new ValidationException("Продукт не прошел проверку валидации" ,errors);
        }
    }
}
