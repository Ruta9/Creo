package com.example.demo.controllers;

import com.example.demo.data.User;
import com.example.demo.exceptions.ObjectNotFoundException;
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
    private UserService userService;

    @GetMapping("/identity")
    public String getFullName() throws ObjectNotFoundException {
        User user = userService.getUser();
        return user.getFirstname() + " " + user.getLastname();
    }

    @GetMapping("/isAuthenticated")
    public Boolean isAuthenticated(Principal user) {
        return user != null;
    }

    @PostMapping
    public ResponseEntity register(@Valid @RequestBody RegistrationForm registrationForm)
            throws UserAlreadyExistsException {
        userService.register(registrationForm);
        return ResponseEntity.ok().build();
    }
}
