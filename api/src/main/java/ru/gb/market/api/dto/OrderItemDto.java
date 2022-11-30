package ru.gb.market.api.dto;

import java.math.BigDecimal;

public class OrderItemDto {

    private Long id;
    private Long orderId;
    private String productTitle;
    private int count;
    private BigDecimal pricePerProduct;
    private BigDecimal sum;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getProductTitle() {
        return productTitle;
    }
    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }
    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }
    public BigDecimal getSum() {
        return sum;
    }
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
