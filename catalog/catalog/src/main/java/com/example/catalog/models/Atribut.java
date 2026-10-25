package com.example.catalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "atribut")
public class Atribut {
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "atribut_product",
            joinColumns = @JoinColumn(name = "atribut_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )

    private List<Product> products = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private int memory;
    private int ram;

    public Atribut() {
    }

    public Atribut(String color, int memory, int ram) {
        this.color = color;
        this.memory = memory;
        this.ram = ram;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
