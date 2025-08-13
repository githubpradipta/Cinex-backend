package com.pro.CinexBackend.controller;

import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.service.AuthService;
import com.pro.CinexBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User newUser){
        try{
            User user = userService.getUserByEmail(newUser.getEmail());
            if(user!=null) return new ResponseEntity<>("Email existed", HttpStatus.CONFLICT);

            authService.registerUser(newUser);
            return new ResponseEntity<>("user saved", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
