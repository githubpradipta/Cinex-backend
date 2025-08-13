package com.pro.CinexBackend.service;

import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo repo;
    Map<String,String> resBody = new HashMap<>();


    public void registerUser(User user){
        try{
            repo.save(user);
        }
        catch (RuntimeException e) {

            throw new RuntimeException(e);

        }
    }

    public ResponseEntity<?> loginUser(String email, String password){
        try{
            User user = repo.findByEmail(email).orElse(null);
            resBody.put("message","email or password is invalid");
            if(user==null){
                return new ResponseEntity<>(resBody, HttpStatus.BAD_REQUEST);
            }
            if(!user.getPassword().equals(password)){
                return new ResponseEntity<>(resBody, HttpStatus.BAD_REQUEST);
            }
            resBody.clear();

            return ResponseEntity.ok(user);

        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
