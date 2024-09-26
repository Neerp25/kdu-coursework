package com.example.demo.entity;

import com.example.demo.Company;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private int id;
    private Company carName;
    private Speaker speaker;
    private Tyre tyre;
    private int price;
}
