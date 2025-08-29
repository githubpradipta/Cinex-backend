package com.pro.CinexBackend.dots.UserDTOs;

import com.pro.CinexBackend.dots.BookingDTOs.BookingDTO;
import com.pro.CinexBackend.dots.MovieDTOs.MovieDTO;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private List<BookingDTO> bookings;
}
