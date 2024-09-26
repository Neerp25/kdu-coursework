package org.example.data;


import org.example.entity.Vehicle;


import java.util.ArrayList;
import java.util.List;


public class VehiclesInventory {

    public List<Vehicle> listofvehicles;

    public VehiclesInventory(){
        listofvehicles= new ArrayList<>();
    }

    public void setListofvehicles(List<Vehicle> listofvehicles) {
        this.listofvehicles = listofvehicles;
    }

    public List<Vehicle> getListofvehicles() {
        return listofvehicles;
    }

    public Vehicle getVehicleWithMaxPrice() {
        if (listofvehicles== null || listofvehicles.isEmpty()) {
            return null;
        }

        Vehicle maxPriceVehicle = listofvehicles.get(0);
        for (Vehicle vehicle : listofvehicles) {
            if (vehicle.getPrice() > maxPriceVehicle.getPrice()) {
                maxPriceVehicle = vehicle;
            }
        }

        return maxPriceVehicle;
    }

    public Vehicle getVehicleWithMinPrice() {
        if ( listofvehicles == null || listofvehicles.isEmpty()) {
            return null;
        }

        Vehicle minPriceVehicle = listofvehicles.get(0);
        for (Vehicle vehicle : listofvehicles) {
            if (vehicle.getPrice() < minPriceVehicle.getPrice()) {
                minPriceVehicle = vehicle;
            }
        }

        return minPriceVehicle;
    }

}
