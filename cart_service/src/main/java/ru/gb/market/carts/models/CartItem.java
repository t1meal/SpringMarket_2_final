package ru.gb.market.carts.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gb.market.api.dto.ProductDto;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CartItem implements Serializable {

    private Long productId;
    private String title;
    private BigDecimal price;
    private int count;
    private BigDecimal sum;

    public CartItem(ProductDto product) {
        this.productId = product.getId();
        this.title = product.getTitle();
        this.count = 1;
        this.price = product.getPrice();
        this.sum = product.getPrice();
    }

    public void changeQuantity(int delta) {
        this.count += delta;
        this.sum = price.multiply(BigDecimal.valueOf(count));
    }
}
