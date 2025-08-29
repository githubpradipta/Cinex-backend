package com.pro.CinexBackend.service;

import com.pro.CinexBackend.dots.BookingDTOs.BookingDTO;
import com.pro.CinexBackend.dots.UserDTOs.UserResponse;
import com.pro.CinexBackend.dots.UserUpdateRequest;
import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;
    private final ModelMapper modelMapper;

    Map<String, String> resbody = new HashMap<>();

    public ResponseEntity<?> getUserById(UUID id){
        try{
            User user = repo.findById(id).orElse(null);
            if(user==null){
                resbody.put("message","User not found");
                return new ResponseEntity<>(resbody,HttpStatus.NOT_FOUND);
            }

            //DTO conversion
            UserResponse userRes = modelMapper.map(user,UserResponse.class);
            if(user.getBookings().size()!=0){
                List<BookingDTO> bookingDTOs = user.getBookings().stream()
                        .map(booking -> {
                            BookingDTO dto = modelMapper.map(booking, BookingDTO.class);
                            dto.setMovieName(booking.getMovie().getTitle());
                            return dto;

                        })
                        .collect(Collectors.toList());
                userRes.setBookings(bookingDTOs);
            }

            return ResponseEntity.ok(userRes);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
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

    @Transactional
    public ResponseEntity<?> deleteUserById(UUID id) {
        try{
            User user = repo.findById(id).orElse(null);
            if(user==null){
                resbody.put("message","user not found");
                return new ResponseEntity<>(resbody,HttpStatus.NOT_FOUND);
            }
            repo.deleteById(id);

            resbody.put("message","user deleted");
            return new ResponseEntity<>(resbody,HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
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

            User updatedUser = repo.save(user);

            resbody.clear();
            resbody.put("message","User updated");
            return ResponseEntity.ok(updatedUser);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
