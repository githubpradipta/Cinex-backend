package com.pro.CinexBackend.service;

import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo repo;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    Map<String,String> resBody = new HashMap<>();


    public void registerUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repo.save(user);
        }
        catch (RuntimeException e) {

            throw new RuntimeException(e);

        }
    }

    public ResponseEntity<?> loginUser(String email, String password){
        try{

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,password)
            );

            String role = auth.getAuthorities()
                    .stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse(null);

            String token = jwtService.generateToken(email,role);

            return ResponseEntity.ok(Map.of(
                    "massage","Logged in",
                    "token",token
            ));


        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
