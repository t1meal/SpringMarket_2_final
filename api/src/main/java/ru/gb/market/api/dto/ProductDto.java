package ru.gb.market.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель товара")
public class ProductDto {
    @Schema (description = "Id товара", required = true, example = "1")
    private Long id;

    @Schema (description = "Название товара", required = true, maxLength = 255,  minLength = 3, example = "Apple")
    private String title;
    @Schema (description = "Цена товара", required = true, example = "100")
    private BigDecimal price;

    @Schema (description = "Принадлежность товара к какой-либо категории", required = true, example = "Food")
    private String categoryTitle;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public ProductDto() {
    }

    public ProductDto(Long id, String title, BigDecimal price, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categoryTitle = categoryTitle;
    }
}
