package com.pro.CinexBackend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JWTService {
    private static final String JWT_SECRET = "sjbsbsdhbhbBHDBHbhhf_93r932rnen_JSBFBJfd84r9enfn";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

    private Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(String email, String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role",role)
                .setIssuer("Pradipta")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60*30)))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String extractEmail(String token){
        return extractClaims(token).getSubject();
    }

    public String extractRole(String token){
        return extractClaims(token).get("role",String.class);
    }

    public Date extractExpiration(String token){
        return extractClaims(token).getExpiration();
    }

    public boolean isTokenNotExpired(String token){
        return extractExpiration(token).after(new Date());
    }

    public boolean validate(String token, UserDetails userDetails){
        String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && isTokenNotExpired(token));
    }
}
