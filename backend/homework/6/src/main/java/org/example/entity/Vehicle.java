package org.example.entity;

import org.example.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Vehicle {

    @Autowired
    private final Tyre tyre;

    @Autowired
    private final Speaker speaker;
    private final int price;

    public Vehicle(Tyre tyre, Speaker speaker, int price) {
        this.tyre = tyre;
        this.speaker = speaker;
        this.price = price + tyre.getPrice() + speaker.getPrice();
    }

    @Autowired
    private VehicleService vehicleService;
    @PostConstruct
    private void postConstruct(){
        vehicleService.generateVehicleList();
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
