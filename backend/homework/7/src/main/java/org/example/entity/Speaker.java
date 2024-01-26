package org.example.entity;




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
