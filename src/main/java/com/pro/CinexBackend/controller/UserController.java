package com.pro.CinexBackend.controller;

import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userservice;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id){
        try{
            User user = userservice.getUserById(id);
            if(user!=null) return ResponseEntity.ok(user);
            else return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getUser(){
        try{
            List<User> users = userservice.getAllUsers();
            return ResponseEntity.ok(users);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
