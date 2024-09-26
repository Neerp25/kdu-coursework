package com.example.demo.dto;

import com.example.demo.Company;
import com.example.demo.entity.Speaker;
import com.example.demo.entity.Tyre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleResdto {
    private int id;
    private Company carName;
    private Speaker speaker;
    private Tyre tyre;
    private int price;
    private String response;
    public VehicleResdto(String response){
        this.response = response;
    }
}
