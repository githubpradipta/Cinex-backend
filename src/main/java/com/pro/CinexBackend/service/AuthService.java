package com.pro.CinexBackend.service;

import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo repo;

    public void registerUser(User user){
        try{
            repo.save(user);
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);

        }
    }
}
