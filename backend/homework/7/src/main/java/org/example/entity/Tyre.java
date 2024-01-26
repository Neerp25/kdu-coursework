package org.example.entity;

import org.springframework.stereotype.Component;


public class Tyre {
    private final int price;
    private final String brand;

    public Tyre(int price, String brand) {
        this.price = price;
        this.brand = brand;
    }

    // Getters

    public int getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Tyre [brand=" + brand + ", price=" + price + "]";
    }
}
