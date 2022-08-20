package ru.gb.market.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDto {
    private Integer id;
    private String title;
    private Integer count;
    private Integer price;
    private Integer sum;
}
