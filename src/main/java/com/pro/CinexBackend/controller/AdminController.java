package com.pro.CinexBackend.controller;

import com.pro.CinexBackend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/people")
    public ResponseEntity<?> getAllByRole(@RequestParam("role") String role){
        return adminService.getAllByRoll(role);
    }


    @PatchMapping("/users/{userId}/role")
    public ResponseEntity<?> changeUserRole(@PathVariable UUID id, @RequestBody String role){
        return adminService.changeUserRole(id,role);
    }
}
