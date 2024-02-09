package com.example.Miniproject.expection;

public class ResourceNotFoundExp extends RuntimeException{
    public ResourceNotFoundExp(String msg){
        super(msg);
    }
}
