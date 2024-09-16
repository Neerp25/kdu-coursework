package com.example.Miniproject.dto.response.entityresponse;

import com.example.Miniproject.entity.model.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Roomresdto {
    String message;
    Room room;
    HttpStatus httpStatus;
}
