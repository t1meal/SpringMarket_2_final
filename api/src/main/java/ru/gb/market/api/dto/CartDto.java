package ru.gb.market.api.dto;

import java.io.Serializable;
import java.util.List;

public class CartDto implements Serializable {

    private Long id;
    private List<CartItemDto> items;
    private int totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
