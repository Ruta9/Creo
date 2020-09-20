package com.example.demo.controllers.controlleradvices;

import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.security.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.AbstractMap;

@ControllerAdvice
public class ExceptionsControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessForbiddenException.class)
    public AbstractMap.SimpleEntry<String, String> handleAccessForbidden(AccessForbiddenException ex) {
        return new AbstractMap.SimpleEntry<>("error", ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public AbstractMap.SimpleEntry<String, String> handleObjectNotFound(ObjectNotFoundException ex) {
        return new AbstractMap.SimpleEntry<>("error", ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public AbstractMap.SimpleEntry<String, String> handleUserAlreadyExists (UserAlreadyExistsException ex) {
        return new AbstractMap.SimpleEntry<>("error", ex.getMessage());
    }
}
