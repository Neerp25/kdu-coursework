package com.example.Miniproject.dto.response.entityresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Responsedto {
    private String message;
    private String object;
    private HttpStatus httpStatus;
}
