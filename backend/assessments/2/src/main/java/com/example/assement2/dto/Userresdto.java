package com.example.assement2.dto;

import com.example.assement2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Userresdto {
    private String message;
    private User object;
    private HttpStatus httpStatus;
}
