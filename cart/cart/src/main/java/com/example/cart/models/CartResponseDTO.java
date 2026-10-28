package com.example.cart.models;

import java.util.List;

public class CartResponseDTO {
    private Long id;
    private Long tovarId;
    private String title;
    private String description;
    private Double price;
    private int count;
    private List<CartAtributeDTO> attributes;

    public CartResponseDTO(Long id, Long tovarId, String title, String description, Double price, int count, List<CartAtributeDTO> attributes) {
        this.id = id;
        this.tovarId = tovarId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.count = count;
        this.attributes = attributes;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CartAtributeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CartAtributeDTO> attributes) {
        this.attributes = attributes;
    }
}
