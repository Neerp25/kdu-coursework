package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cordinatesdto {
    double latitude;
    double longitude;
    @JsonIgnore
    String region;

    public String print(){
        return "[latitude: " + latitude + ", longitude: " + longitude + "]";
    }
}
