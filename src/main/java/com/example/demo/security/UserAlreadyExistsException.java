package com.example.demo.security;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException (String message){
        super(message);
    }
}
