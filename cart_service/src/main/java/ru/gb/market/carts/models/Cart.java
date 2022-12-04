package ru.gb.market.carts.models;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    private BigDecimal totalPrice;

    public Cart(Long userID) {
        this.id = userID;
        this.items = new ArrayList<>();
        this.totalPrice = BigDecimal.ZERO;
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
