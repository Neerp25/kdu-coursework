package com.example.Miniproject.expection;

public class InvalidAcess extends RuntimeException{
    public InvalidAcess(String msg){
        super(msg);
    }
}
