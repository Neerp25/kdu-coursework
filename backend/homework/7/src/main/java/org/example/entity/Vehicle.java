package org.example.entity;


public class Vehicle {


    private final Tyre tyre;


    private final Speaker speaker;
    private final int price;

    public Vehicle(Tyre tyre, Speaker speaker, int price) {
        this.tyre = tyre;
        this.speaker = speaker;
        this.price = price + tyre.getPrice() + speaker.getPrice();
    }



    public Tyre getTyre() {
        return tyre;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Vehicle [tyre=" + tyre + ", speaker=" + speaker + ", price=" + price + "]";
    }
}
