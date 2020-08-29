package com.example.demo.controllers;

import com.example.demo.data.User;
import com.example.demo.security.UserContext;
import com.example.demo.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
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
    public Boolean isAuthenticated() {
        return userContext.isAuthenticated();
    }
}
