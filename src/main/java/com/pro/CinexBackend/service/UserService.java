package com.pro.CinexBackend.service;

import com.pro.CinexBackend.dto.UserUpdateRequest;
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
public class UserService {

    private final UserRepo repo;
    Map<String, String> resbody = new HashMap<>();

    public User getUserById(UUID id){
        try{
            return repo.findById(id).orElse(null);
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByEmail(String email) {
        try{
            return repo.findByEmail(email).orElse(null);
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try{
            return repo.findAll();
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserById(UUID id) {
        try{
            repo.deleteById(id);
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> updateUserById(UUID id, UserUpdateRequest data){
        try{
            User user = repo.findById(id).orElse(null);

            if(user==null){
                resbody.put("message","User not found");
                return new ResponseEntity<>(resbody, HttpStatus.NOT_FOUND);
            }
            if(data.getName()!=null) user.setName(data.getName());
            if(data.getEmail()!=null) user.setEmail(data.getEmail());
            if(data.getPassword()!=null) user.setPassword(data.getPassword());
            if(data.getRole()!=null) user.setRole(data.getRole());

            repo.save(user);

            resbody.clear();
            resbody.put("message","User updated");
            return ResponseEntity.ok(resbody);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
