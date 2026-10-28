package com.example.cart.models;

public class CartAtributeDTO {
    private Long id;
    private String color;
    private int memory;
    private int ram;

    public CartAtributeDTO() {
    }

    public CartAtributeDTO(Long id, String color, int memory, int ram) {
        this.id = id;
        this.color = color;
        this.memory = memory;
        this.ram = ram;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}