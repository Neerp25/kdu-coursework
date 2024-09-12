package com.example.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Errordto {

        private String msg;
        private int statusCode;

}
