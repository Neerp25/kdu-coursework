package org.example.services;

import org.example.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@ComponentScan(basePackages = {"org.example.entity","org.example.services"})
public class VehicleService {

    @Autowired
    private TyreService tyreService;

    @Autowired
    private SpeakerService speakerService;

    Random random = new Random();

    @Bean(name = "vehiclegenerate")
    public List<Vehicle> generateVehicleList(){
        List<Vehicle> vehicles = new ArrayList<>();
        for(int i=1;i<=5;i++){
            int price = random.nextInt(5_000_000)+100_000;
            Vehicle vehicle = new Vehicle(tyreService.generateTyre(),speakerService.generateSpeaker(),price);
            vehicles.add(vehicle);
        }
        return vehicles;
    }

    @Bean(name = "mostExpenciveVehicle")
    public Vehicle mostExpecive(List<Vehicle> vehicleList){
        int n = vehicleList.size();
        double max=-1;
        Vehicle veh = null;
        for(Vehicle temp:vehicleList){
            double price = temp.getPrice();
            if(price>max){
                max=price;
                veh = temp;
            }
        }
        return veh;
    }

}
