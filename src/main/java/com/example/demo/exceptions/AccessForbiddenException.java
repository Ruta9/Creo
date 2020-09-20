package com.example.demo.exceptions;

public class AccessForbiddenException extends Exception{

    public AccessForbiddenException(String message){
        super(message);
    }

    public AccessForbiddenException(){
        super("You do not have the required access rights to complete the action");
    }
}
