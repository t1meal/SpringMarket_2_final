package ru.gb.market.core.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.gb.market.api.dto.ProductDto;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "product_title")
    private String title;

    @Column(name = "count")
    private int count;

    @Column(name = "price_per_product")
    private int pricePerProduct;

    @Column(name = "sum")
    private int sum;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

/////////
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(String title, int count, int pricePerProduct, int sum) {
        this.title = title;
        this.count = count;
        this.pricePerProduct = pricePerProduct;
        this.sum = sum;
    }
}
