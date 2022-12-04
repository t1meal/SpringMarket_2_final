package ru.gb.market.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class CartItemDto implements Serializable {

    private Long productId;
    private String title;
    private BigDecimal price;
    private int quantity;
    private BigDecimal sum;


    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getSum() {
        return sum;
    }
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public CartItemDto(Long productId, String title, BigDecimal price, int quantity, BigDecimal sum) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.sum = sum;
    }
    public CartItemDto() {
    }
}
