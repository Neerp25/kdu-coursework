package com.example.demo.service;

import com.example.demo.dto.VehicleReqdto;
import com.example.demo.dto.VehicleResdto;
import com.example.demo.entity.Vehicle;
import com.example.demo.inventory.VehicleInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VehicleService {

    private String NotExist = "Vehicle not exist";
    private final VehicleInventory inventory;
    @Autowired
    public VehicleService(VehicleInventory inventory){
        this.inventory = inventory;
    }

    public Vehicle getVehicleFromRequestDTO(VehicleReqdto vehicleRequestDTO){
        return new Vehicle(vehicleRequestDTO.getId(),vehicleRequestDTO.getCarName(),vehicleRequestDTO.getSpeaker(),vehicleRequestDTO.getTyre(),vehicleRequestDTO.getPrice());
    }
    /**
     * converts vehicle to response DTO
     */
    public VehicleResdto getVehicleResponseFromVehicle(Vehicle vehicle, String response){

        return new VehicleResdto(vehicle.getId(),vehicle.getCarName(),vehicle.getSpeaker(),vehicle.getTyre(), vehicle.getPrice(),response);
    }

    public VehicleResdto addVehicle(VehicleReqdto vehicleRequestDTO){
        Vehicle vehicle = inventory.getVehicle(vehicleRequestDTO.getId());
        if(Objects.isNull(vehicle)){
            vehicle = getVehicleFromRequestDTO(vehicleRequestDTO);
        }
        else {
            return getVehicleResponseFromVehicle(vehicle,"Vehicle with same id already exists");
        }
        inventory.addVehicle(vehicle);
        return getVehicleResponseFromVehicle(vehicle, "Vehicle added successfully");
    }

    public VehicleResdto getVehicle(int id){
        Vehicle vehicle = inventory.getVehicle(id);
        if(Objects.isNull(vehicle)){
            return new VehicleResdto(NotExist);
        }
        return getVehicleResponseFromVehicle(vehicle,"Vehicle fetched");

    }

    public VehicleResdto updateVehicle(int id,VehicleReqdto newVehicle){
        Vehicle vehicle = inventory.getVehicle(id);
        if(Objects.isNull(vehicle)){
            return new VehicleResdto(NotExist);
        }
        else {
            Vehicle nVehicle = getVehicleFromRequestDTO(newVehicle);
            inventory.updateVehicle(vehicle,nVehicle);
            return getVehicleResponseFromVehicle(nVehicle,"Vehicle updated successfully");
        }
    }
    public VehicleResdto removeVehicle(int id){
        Vehicle vehicle = inventory.getVehicle(id);
        if(Objects.isNull(vehicle)){
            return new VehicleResdto(NotExist);
        }
        else {
            inventory.deleteVehicle(vehicle);
            return getVehicleResponseFromVehicle(vehicle,"Vehicle deleted successfully");
        }
    }

    public VehicleResdto getHighLowVehicle(int type){
        Vehicle vehicle;
        if(type == 1){
            vehicle = inventory.getMostExpensive();
        }
        else {
            vehicle = inventory.getLeastExpensice();
        }
        return getVehicleResponseFromVehicle(vehicle,"");
    }




}
