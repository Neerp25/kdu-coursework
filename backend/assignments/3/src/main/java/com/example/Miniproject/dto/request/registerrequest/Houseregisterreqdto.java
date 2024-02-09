package com.example.Miniproject.dto.request.registerrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Houseregisterreqdto {
    String address;
    @JsonProperty("house_name")
    String houseName;
}
