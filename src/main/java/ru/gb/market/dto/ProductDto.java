package ru.gb.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.market.model.Product;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }

}
