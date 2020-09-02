package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

@Component
public class UserContext {

    public String getEmail() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!auth.isAuthenticated()) {
            return null;
        }
        if (auth.getPrincipal() == null) {
            return null;
        }

         try {
                DefaultOidcUser defaultOidcUser = (DefaultOidcUser) auth.getPrincipal();
                return defaultOidcUser.getEmail();
            } catch (ClassCastException ex) {
                return (String)auth.getPrincipal();
            }
    }

}

