package com.example.demo.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCredentials {

    private String email;
    private String password;
}
