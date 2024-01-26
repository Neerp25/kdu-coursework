package org.example.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class Speaker {

    private int price;
    private String brand;


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
