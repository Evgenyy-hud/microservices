package com.example.cart.models;

import jakarta.persistence.*;


@Entity
@Table(name= "cart_product", uniqueConstraints = {
        @UniqueConstraint(name="cart_product_tovar_id", columnNames= {"tovar_id"})
})

public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tovar_id", nullable = false)
    private Long tovarId;

    @Column(name = "count", nullable = false)
    private int count;

    public CartProduct() {
    }

    public CartProduct(Long id, Long tovarId, int count) {
        this.id = id;
        this.tovarId = tovarId;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
