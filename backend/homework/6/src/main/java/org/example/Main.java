package org.example;


import org.example.entity.Vehicle;
import org.example.services.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext vehicleContext = new AnnotationConfigApplicationContext(VehicleService.class);
        List<Vehicle> vehicleList = (List<Vehicle>) vehicleContext.getBean("vehiclegenerate");
        ConsoleLogger.infoMethod("List of generated Vehicles,");
        for (Vehicle veh: vehicleList){
            ConsoleLogger.infoMethod(veh.toString());
        }

    }
}