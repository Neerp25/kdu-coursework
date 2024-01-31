package com.example.demo.exceptions;

public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException(String msg){
        super(msg);
    }
}
