package com.example.webapp.dto;

import com.example.webapp.Company;
import com.example.webapp.entity.Speaker;
import com.example.webapp.entity.Tyre;
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
