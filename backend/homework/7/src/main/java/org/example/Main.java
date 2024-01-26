package org.example;

import org.example.config.AppConfig;
import org.example.data.VehiclesInventory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        VehiclesInventory factoryoneInventory = (VehiclesInventory) context.getBean("factoryoneInventory");
        VehiclesInventory factorysecondInventory = (VehiclesInventory) context.getBean("factorysecondInventory");
        ConsoleLogger.infoMethod("Number of Vehicles in Factoryone inventory: " + factoryoneInventory.getListofvehicles().size());
        ConsoleLogger.infoMethod("For Factoryone inventory max and min vehicles:");
        ConsoleLogger.infoMethod(factoryoneInventory.getVehicleWithMaxPrice().toString());
        ConsoleLogger.infoMethod(factoryoneInventory.getVehicleWithMinPrice().toString());

        ConsoleLogger.infoMethod("Number of Vehicles in Factorysecond inventory:" + factorysecondInventory.getListofvehicles().size());
        ConsoleLogger.infoMethod("For Factorysecond inventory max and min vehicles:");
        ConsoleLogger.infoMethod(factorysecondInventory.getVehicleWithMaxPrice().toString());
        ConsoleLogger.infoMethod(factorysecondInventory.getVehicleWithMinPrice().toString());



    }
}