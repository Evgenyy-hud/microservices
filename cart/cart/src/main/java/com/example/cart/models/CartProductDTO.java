package com.example.cart.models;

import java.util.List;

public class CartProductDTO {
    private Long id;
    private Long tovarId;
    private String description;
    private Double price;
    private Integer count;
    private String title;
    private List<CartAtributeDTO> attributes; // <-- Добавили список атрибутов

    public CartProductDTO() {
    }

    public CartProductDTO(Long id, Long tovarId, String description, Double price, Integer count, String title, List<CartAtributeDTO> attributes) {
        this.id = id;
        this.tovarId = tovarId;
        this.description = description;
        this.price = price;
        this.count = count;
        this.title = title;
        this.attributes = attributes; // <-- Добавили в конструктор
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTovarId() {
        return tovarId;
    }

    public void setTovarId(Long tovarId) {
        this.tovarId = tovarId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public List<CartAtributeDTO> getAttributes() {
        return attributes;
    }


    public void setAttributes(List<CartAtributeDTO> attributes) {
        this.attributes = attributes;
    }
}