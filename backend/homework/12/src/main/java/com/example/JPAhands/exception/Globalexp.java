package com.example.JPAhands.exception;

import dto.Errordto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class Globalexp {

    /**
     * Exception handling  for instances of Data Not Match And Return error details and HTTP Status.
     */
    @ExceptionHandler(value = {Datanotmatch.class})
    public ResponseEntity<Errordto> handleDataNotMatchException(Datanotmatch ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    /**
     * Exception handling  for instances of User Not Found And Return error details and HTTP Status.
     */
    @ExceptionHandler(value = {Usernotfoundexp.class})
    public ResponseEntity<Errordto> handleUserNotFoundException(Usernotfoundexp ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handling  for instances of Tanant Not Found And Return error details and HTTP Status.
     */
    @ExceptionHandler(value = {Tanantnotfoundexp.class})
    public ResponseEntity<Errordto> handleTenantNotFoundException(Tanantnotfoundexp ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handling  for instances of Shift Not Found And Return error details and HTTP Status.
     */
    @ExceptionHandler(value = {Shiftnotfoundexp.class})
    public ResponseEntity<Errordto> handleShiftNotFoundException(Shiftnotfoundexp ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    /**
     * Exception handling  for instances of Unpronounceable Entity And Return error details and HTTP Status.
     */
    @ExceptionHandler(value = {unprocesEntityexp.class})
    public ResponseEntity<Errordto> handleUnProcessableEntityException(unprocesEntityexp ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Errordto> handleException(Exception ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
