package ru.gb.market.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class CartItemDto implements Serializable {

    private Long productId;
    private String title;
    private BigDecimal price;
    private int count;
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
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public BigDecimal getSum() {
        return sum;
    }
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public CartItemDto(Long productId, String title, BigDecimal price, int count, BigDecimal sum) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.count = count;
        this.sum = sum;
    }
    public CartItemDto() {
    }
}
