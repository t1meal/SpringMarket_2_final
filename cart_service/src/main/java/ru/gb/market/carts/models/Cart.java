package ru.gb.market.carts.models;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
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
        this.items = new ArrayList<>();
        this.totalPrice = 0;
    }

//    public List<CartItem> getItems (){
//        return Collections.unmodifiableList(items);
//    }

    public void setNewItem (CartItem cartItem){
        List<CartItem> cartItems = new ArrayList<>(this.items);
        cartItems.add(cartItem);
        setItems(cartItems);
    }

}
