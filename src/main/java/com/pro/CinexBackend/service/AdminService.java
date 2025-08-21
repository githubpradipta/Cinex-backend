package com.pro.CinexBackend.service;

import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepo userRepo;
    Map<String, String> resbody = new HashMap<>();

    public ResponseEntity<?> getAllByRoll(String roll) {
        try{
            List<User> users = userRepo.findByRole(roll);
            return ResponseEntity.ok(users);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    public ResponseEntity<?> changeUserRole(UUID id, String role){
        try{
            User user = userRepo.findById(id).orElse(null);
            if(user==null){
                resbody.put("message","user not found");
                return new ResponseEntity<>(resbody, HttpStatus.NOT_FOUND);
            }
            user.setRole(role);
            userRepo.save(user);

            resbody.put("message","role changed");
            return new ResponseEntity<>(resbody,HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
