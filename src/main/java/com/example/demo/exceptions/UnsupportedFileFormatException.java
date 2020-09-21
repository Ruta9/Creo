package com.example.demo.exceptions;

public class UnsupportedFileFormatException extends Exception{

    public UnsupportedFileFormatException(String message){
        super(message);
    }

    public UnsupportedFileFormatException(){
        super("Uploaded file format is not supported");
    }
}
