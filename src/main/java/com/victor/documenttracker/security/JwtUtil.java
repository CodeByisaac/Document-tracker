//generate and validate JWT
package com.victor.documenttracker.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationMs = 86400000; //24 hours

    //generate JWT token for authenticated user
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username) //username as token subject
                .setIssuedAt(new Date()) //current time as issue time
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey) //use secretkey to sign
                .compact(); //builds the token string

    }

    // validate and parse(decode) the token
    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token) //verifies signature and decodes claims
                .getBody()
                .getSubject(); //get username from payload
    }
}

