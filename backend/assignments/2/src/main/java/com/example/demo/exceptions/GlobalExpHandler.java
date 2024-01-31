package com.example.demo.exceptions;

import com.example.demo.dto.Errordto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExpHandler {
    /**
     * Handling InvalidArugumentExpection
     */
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<Errordto> handleException(InvalidArgumentException exception){
        Errordto errorDTO = new Errordto(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.NOT_FOUND);
    }
    /**
     * Handling HttpMessageNotReadable
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Errordto> methodArgument(HttpMessageNotReadableException exception){
        Errordto errorDTO = new Errordto("Method arguments is invalid", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }
    /**
     * Handling RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Errordto> handleError(RuntimeException exception){
        Errordto errorDTO = new Errordto(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }
}
