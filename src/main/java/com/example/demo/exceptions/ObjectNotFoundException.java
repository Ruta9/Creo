package com.example.demo.exceptions;

public class ObjectNotFoundException extends Exception{

    public ObjectNotFoundException(String message){
        super(message);
    }

    public ObjectNotFoundException(){
        super("Object not found");
    }
}
