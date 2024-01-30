package com.example.webapp.exceptions;

public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException(String msg){
        super(msg);
    }
}
