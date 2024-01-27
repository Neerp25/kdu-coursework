package com.example.demo.inventory;

import com.example.demo.entity.Vehicle;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Data
@Repository
public class VehicleInventory {

    List<Vehicle> vehicles = new ArrayList<>();
    public Vehicle getVehicle(int id){
        for(Vehicle temp: vehicles){
            if(temp.getId() == id){
                return temp;
            }
        }
        return null;
    }
    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }
    public void updateVehicle(Vehicle vehicle,Vehicle newVehicle){
        int index = vehicles.indexOf(vehicle);
        vehicles.set(index,newVehicle);
    }
    public void deleteVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);
    }
    public Vehicle getMostExpensive(){
        if (vehicles== null || vehicles.isEmpty()) {
            return null;
        }

        Vehicle maxPriceVehicle = vehicles.get(0);
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPrice() > maxPriceVehicle.getPrice()) {
                maxPriceVehicle = vehicle;
            }
        }

        return maxPriceVehicle;

    }
    public Vehicle getLeastExpensice(){
        if ( vehicles == null || vehicles.isEmpty()) {
            return null;
        }

        Vehicle minPriceVehicle = vehicles.get(0);
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPrice() < minPriceVehicle.getPrice()) {
                minPriceVehicle = vehicle;
            }
        }

        return minPriceVehicle;

    }
}
