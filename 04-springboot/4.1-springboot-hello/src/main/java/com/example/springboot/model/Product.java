package com.example.springboot.model;

import java.util.Objects;

/**
 * @author Manoel Campos
 */
public class Product {
    private static long currentId = 0;

    private Long id;
    private String name;
    private double price;

    public Product() {
    }

    public Product(String name, double price) {
        this(++currentId, name, price);
    }

    public Product(long id, String name, double price) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id == null || id < 0 ? ++currentId : id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{id=%d, name='%s', price=%s}".formatted(id, name, price);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Product product))
            return false;

        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
