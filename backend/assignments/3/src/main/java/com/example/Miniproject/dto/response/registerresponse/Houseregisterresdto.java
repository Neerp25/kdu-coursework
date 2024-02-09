package com.example.Miniproject.dto.response.registerresponse;

import com.example.Miniproject.entity.model.House;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Houseregisterresdto {
    String message;
    House house;
    HttpStatus httpStatus;
}
