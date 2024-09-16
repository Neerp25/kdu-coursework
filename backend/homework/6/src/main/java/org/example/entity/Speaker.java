package org.example.entity;

import org.springframework.stereotype.Component;

@Component
public class Speaker {

    private final int price;
    private final String brand;

    public Speaker(int price, String brand) {
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
        return "Speaker [brand=" + brand + ", price=" + price + "]";
    }
}
