package com.example.demo.exceptions;

public class FileStorageException extends Exception{

    public FileStorageException(String message){
        super(message);
    }

    public FileStorageException(){
        super("File Storage error");
    }
}
