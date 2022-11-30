package ru.gb.market.api.dto;
import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private Long id;
    private String username;
    private List<OrderItemDto> items;
    private String userEmail;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<OrderItemDto> getItems() {
        return items;
    }
    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }
    public String getUser_email() {
        return userEmail;
    }
    public void setUser_email(String user_email) {
        this.userEmail = user_email;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
