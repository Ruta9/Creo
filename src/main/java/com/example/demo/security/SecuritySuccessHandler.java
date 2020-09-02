package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        DefaultOidcUser oidcuser = ((DefaultOidcUser)authentication.getPrincipal());
        userService.register(
                oidcuser.getGivenName(),
                oidcuser.getFamilyName(),
                oidcuser.getEmail()
        );

        AuthenticationService.addJWTToken(httpServletResponse, oidcuser.getEmail());
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }
}
