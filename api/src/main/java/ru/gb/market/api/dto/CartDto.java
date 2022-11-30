package ru.gb.market.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CartDto implements Serializable {

    private Long id;
    private List<CartItemDto> items;
    private BigDecimal totalPrice;

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
