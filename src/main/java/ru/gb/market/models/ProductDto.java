package ru.gb.market.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.gb.market.entities.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotNull(message = "Title is empty!")
    @Length (min = 3, max = 50, message = "Incorrect title length!")
    private String title;
    @Min(value = 1, message = "Price is too small!")
    private int price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }

}
