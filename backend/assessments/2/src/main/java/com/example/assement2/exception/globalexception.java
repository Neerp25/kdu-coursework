package com.example.assement2.exception;

import com.example.assement2.dto.Errordto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class globalexception {

    //exception handler for user not found

    @ExceptionHandler(value = {Usernotfoundexp.class})
    public ResponseEntity<Errordto> handleUserNotFoundException(Usernotfoundexp ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    //exception handler for Event not found
    @ExceptionHandler(value = {Eventnotfoundexp.class})
    public ResponseEntity<Errordto> handleEventNotFoundException(Eventnotfoundexp ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
