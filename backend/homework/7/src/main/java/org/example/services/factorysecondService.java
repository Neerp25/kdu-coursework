package org.example.services;

import org.example.data.VehiclesInventory;
import org.example.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class factorysecondService implements InterfaceVehicleService {

    @Autowired
    @Qualifier("factorysecondInventory")
    public VehiclesInventory teslaInventory;

    @Autowired
    private SpeakerService speakerService;


    private TyreService tyreService;

    // use setter injection here
    @Autowired
    public void setTyreService(TyreService tyreService) {
        this.tyreService = tyreService;
    }


    List<Vehicle> vehicles = new ArrayList<>();

    @PostConstruct
    public void run(){
        generateVehicleList();
    }


    Random random = new Random();
    @Override
    public void generateVehicleList() {
        for(int i=1;i<=5;i++){
            int price = (int)((random.nextInt(5_000_000)+100_000)*1.4);
            Vehicle vehicle = new Vehicle(tyreService.generateTyre(),speakerService.generateSpeaker(),price);
            vehicles.add(vehicle);
        }
        teslaInventory.setListofvehicles(vehicles);
    }
}
