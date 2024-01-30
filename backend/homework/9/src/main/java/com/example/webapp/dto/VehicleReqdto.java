package com.example.webapp.dto;

import com.example.webapp.Company;
import com.example.webapp.entity.Speaker;
import com.example.webapp.entity.Tyre;
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
