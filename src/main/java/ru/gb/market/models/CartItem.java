package ru.gb.market.models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CartItem implements Serializable {

    private String title;
    private Integer count;
    private Integer price;
    private Integer sum;
}
