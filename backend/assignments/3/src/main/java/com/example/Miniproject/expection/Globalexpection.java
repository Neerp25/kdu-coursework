package com.example.Miniproject.expection;

import com.example.Miniproject.dto.Errordto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Globalexpection {

    /**
     * Handles general exceptions.
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Errordto> handleException(Exception ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    /**
     * Handles DataNotMatched Exception.
     */
    @ExceptionHandler(value = {Datanotmatchexp.class})
    public ResponseEntity<Errordto> handleDataNotMatchedException(Datanotmatchexp ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles Entity not processed Exception, which occurs when the request is semantically incorrect.
     */
    @ExceptionHandler(value = {Entityunprocessexp.class})
    public ResponseEntity<Errordto> handleEntityNotProcessedException(Entityunprocessexp ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles Invalid Argument
     */
    @ExceptionHandler(value = {ArgumentInvalidexp.class})
    public ResponseEntity<Errordto> handleArgumentInvalidException(ArgumentInvalidexp ex){
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles NotFetchException.
     */
    @ExceptionHandler(value = {NotFetchexp.class})
    public ResponseEntity<Errordto> handleNotFetchException(NotFetchexp ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.BAD_GATEWAY);
        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }

    /**
     * Handles InvalidAccessException
     */
    @ExceptionHandler(value = {InvalidAcess.class})
    public ResponseEntity<Errordto> handleInvalidAccessException(InvalidAcess ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    /**
     * Handles ResourceNotFoundException
     */
    @ExceptionHandler(value = {ResourceNotFoundExp.class})
    public ResponseEntity<Errordto> handleResourceNotFoundException(ResourceNotFoundExp ex) {
        Errordto error = new Errordto(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
