package com.example.demo.controllers;

import com.example.demo.security.RegistrationForm;
import com.example.demo.security.UserAlreadyExistsException;
import com.example.demo.security.UserContext;
import com.example.demo.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.AbstractMap;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserContext userContext;

    @Autowired
    private UserService userService;

    @GetMapping("/identity")
    public String getFullName() {
        return userService.getUserFullName(userContext.getEmail());
    }

    @GetMapping("/isAuthenticated")
    public Boolean isAuthenticated(Principal user) {
        return user != null;
    }

    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationForm registrationForm) {
        try {
            userService.register(registrationForm);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity(new AbstractMap.SimpleEntry<>("error",e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
