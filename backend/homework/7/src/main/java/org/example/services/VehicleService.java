package org.example.services;

import org.example.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@ComponentScan(basePackages = {"org.example.entity","org.example.services"})
public class VehicleService {
    List<Vehicle> vehicles = new ArrayList<>();
    @Autowired
    private TyreService tyreService;

    @Autowired
    private SpeakerService speakerService;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    Random random = new Random();
    @PostConstruct
    public void run(){
        generateVehicleList();
    }
    public void generateVehicleList(){

        for(int i=1;i<=5;i++){
            int price = random.nextInt(5_000_000)+100_000;
            Vehicle vehicle = new Vehicle(tyreService.generateTyre(),speakerService.generateSpeaker(),price);
            vehicles.add(vehicle);
        }
    }


    public Vehicle mostExpecive(){
        int n = vehicles.size();
        double max=-1;
        Vehicle veh = null;
        for(Vehicle temp:vehicles){
            double price = temp.getPrice();
            if(price>max){
                max=price;
                veh = temp;
            }
        }
        return veh;
    }

}
