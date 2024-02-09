package com.example.Miniproject.dto.response.getresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Getroomdevresdto {
    String message;
    String roomsAndDevices;
    HttpStatus httpStatus;
}
