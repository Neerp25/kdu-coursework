package com.example.jdbc.dto;

import com.example.jdbc.entities.Shift;
import com.example.jdbc.entities.Shifttype;
import com.example.jdbc.entities.Shiftuser;
import com.example.jdbc.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entitydto {
    private User user;
    private Shift shift;
    private Shifttype shiftType;
    private Shiftuser shiftUser;
}
