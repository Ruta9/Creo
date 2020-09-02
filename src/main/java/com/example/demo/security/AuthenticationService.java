package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;

import static java.util.Collections.emptyList;

public class AuthenticationService {

    static final long EXPIRATIONTIME = 864_000_00;
    static final String SIGNINGKEY = "creoApplicationKey";
    static final String BEARER_PREFIX = "Bearer";

    // Creates the token and adds it to to Http-only cookie
    static public void addJWTToken(HttpServletResponse response, String email) {
        String JwtToken = Jwts.builder().setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
                .compact();
        response.setHeader("Set-Cookie", "JWT=" + JwtToken +"; HttpOnly; SameSite=strict; Path=/");
        response.setHeader("Access-Control-Allow-Credentials", "true");

    }

    // Extracts the token from the cookie
    static public Authentication getAuthentication(HttpServletRequest request) {
        if (request.getCookies() != null) {
            Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("JWT")).findFirst().orElse(null);

            if (cookie != null){
                String token = cookie.getValue();
                String user = Jwts.parser()
                        .setSigningKey(SIGNINGKEY)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, emptyList());
                } else {
                    throw new RuntimeException("Authentication failed");
                }
            }
        }

        return null;
    }
}
