package ru.gb.market.models;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash ("cart")

public class ShoppingCart implements Serializable {

    @Id
    private Long id;
    private List<CartItem> items;
    private int totalPrice;

    public ShoppingCart(Long userID) {
        this.id = userID;
        this.totalPrice = 0;
    }

    public List<CartItem> getItems (){
        return Collections.unmodifiableList(items); // TODO подредачить код под геттер
    }

}
