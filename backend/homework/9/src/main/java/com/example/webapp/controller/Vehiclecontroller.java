package com.example.webapp.controller;

import com.example.webapp.dto.VehicleReqdto;
import com.example.webapp.dto.VehicleResdto;
import com.example.webapp.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Vehiclecontroller {

    private final VehicleService vehicleService;

    @Autowired
    public Vehiclecontroller(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/add/vehicle")
    public String addVehicle(@Valid @RequestBody VehicleReqdto vehicleRequestDTO){
        return vehicleService.addVehicle(vehicleRequestDTO).getResponse();
    }

    @GetMapping("/search/vehicle")
    public VehicleResdto searchVehicle(@RequestParam int id){
        return vehicleService.getVehicle(id);
    }

    @PutMapping("/update/vehicle/{id}")
    public ResponseEntity<VehicleResdto> updateVehicle(@PathVariable int id, @Valid @RequestBody VehicleReqdto newVehicle){
        return ResponseEntity.ok(vehicleService.updateVehicle(id,newVehicle));
    }

    @DeleteMapping("/delete/vehicle/{id}")
    public String deleteVehicle(@PathVariable int id){
        return vehicleService.removeVehicle(id).getResponse();
    }

    @GetMapping("/get/vehicle/{type}")
    public VehicleResdto getVehicle(@PathVariable String type){
        if(type.equals("highest")){
            return vehicleService.getHighLowVehicle(1);
        }
        else {
            return vehicleService.getHighLowVehicle(0);
        }
    }


}
