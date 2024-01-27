package com.example.demo.dto;

import com.example.demo.Company;
import com.example.demo.entity.Speaker;
import com.example.demo.entity.Tyre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class VehicleReqdto {
    private int id;
    @NonNull
    private Company carName;
    @NonNull
    private Speaker speaker;
    @NonNull
    private Tyre tyre;
    private int price;
}
