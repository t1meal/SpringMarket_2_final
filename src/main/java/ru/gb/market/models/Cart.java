package ru.gb.market.models;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash ("cart")

public class Cart implements Serializable {

    @Id
    private Long id;

    private String cartOwner;

//    @Transient
    private List<CartItem> items = new ArrayList<>();
}
