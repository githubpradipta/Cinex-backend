package com.pro.CinexBackend.controller;

import com.pro.CinexBackend.entity.Booking;
import com.pro.CinexBackend.entity.Movie;
import com.pro.CinexBackend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getBookingsByUserId(@PathVariable UUID userId){
        System.out.println(userId);
        return bookingService.getBookingsbyUserId(userId);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking bookingData, @RequestParam UUID userId, @RequestParam UUID movieId){
        return bookingService.createBooking(bookingData,userId,movieId);
    }

    @PatchMapping ("/{bookingId}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable UUID bookingId){
        return bookingService.cancelBooking(bookingId);
    }

}
