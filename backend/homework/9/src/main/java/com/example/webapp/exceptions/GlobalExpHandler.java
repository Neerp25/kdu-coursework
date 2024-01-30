package com.example.webapp.exceptions;

import com.example.webapp.dto.Errordto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExpHandler {

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<Errordto> handleException(InvalidArgumentException exception){
        Errordto errorDTO = new Errordto(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Errordto> nullPointer(NullPointerException exception){
        Errordto errorDTO = new Errordto("No vehicles present currently", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Errordto> methodArgument(HttpMessageNotReadableException exception){
        Errordto errorDTO = new Errordto("Method arguments is invalid", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }

}
