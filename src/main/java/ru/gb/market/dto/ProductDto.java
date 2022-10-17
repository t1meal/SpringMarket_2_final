package ru.gb.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    @NotNull(message = "Title is empty!")
    @Length(min = 3, max = 50, message = "Incorrect title length!")
    private String title;

    @Min(value = 1, message = "Price is too small!")
    private int price;

}
