package ru.gb.market.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.market.entities.Product;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CartItem implements Serializable {

    private Long id;
    private String title;
    private int price;
    private int count;
    private int sum;

    public CartItem(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.count = 1;
        this.price = product.getPrice();
        this.sum = product.getPrice();
    }

    public void changeQuantity(int delta) {
        this.count += delta;
        this.sum = this.count * this.price;
    }
}
