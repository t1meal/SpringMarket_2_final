package ru.gb.market.carts.models;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash ("cart")

public class Cart implements Serializable {

    @Id
    private Long id;
    private List<CartItem> items;
    private int totalPrice;

    public Cart(Long userID) {
        this.id = userID;
        this.totalPrice = 0;
    }

//    public List<CartItem> getItems (){
//        return Collections.unmodifiableList(items); // TODO подредачить код под геттер
//    }
    public void setNewItem (CartItem cartItem){
        this.items.add(cartItem);
    }

}
