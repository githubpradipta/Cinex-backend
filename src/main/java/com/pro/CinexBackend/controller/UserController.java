package com.pro.CinexBackend.controller;

import com.pro.CinexBackend.dto.UserUpdateRequest;
import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userservice;
    Map<String, String> resbody = new HashMap<>();

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
        try{
            userservice.deleteUserById(id);
            resbody.put("Message","User Deleted");
            return ResponseEntity.ok(resbody);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequest data){
        return userservice.updateUserById(id,data);
    }


}
