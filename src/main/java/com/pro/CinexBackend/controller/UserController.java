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
@RequestMapping("/api/users")
public class UserController {

    private final UserService userservice;
    Map<String, String> resbody = new HashMap<>();

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id){
        return userservice.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
        return userservice.deleteUserById(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequest data){
        return userservice.updateUserById(id,data);
    }


}
