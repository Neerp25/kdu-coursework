package com.example.Miniproject.dto.response.entityresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventoryresdto {
    String inventory;
    HttpStatus httpStatus;
}
