package com.pro.CinexBackend.controller;

import com.pro.CinexBackend.repository.UserRepo;
import com.pro.CinexBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        return userService.getAllUsers();
    }

    @PatchMapping("/users/{userId}/role")
    public ResponseEntity<?> changeUserRole(@PathVariable UUID id, @RequestBody String role){
        return userService.changeUserRole(id,role);
    }
}
