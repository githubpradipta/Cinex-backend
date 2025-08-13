package com.pro.CinexBackend.service;

import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;

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
}
