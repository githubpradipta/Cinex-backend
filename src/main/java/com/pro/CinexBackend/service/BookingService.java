package com.pro.CinexBackend.service;

import com.pro.CinexBackend.entity.Booking;
import com.pro.CinexBackend.entity.Movie;
import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.repository.BookingRepo;
import com.pro.CinexBackend.repository.MovieRepo;
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
public class BookingService {
    private final BookingRepo bookingRepo;
    private final MovieRepo movieRepo;
    private final UserRepo userRepo;

    Map<String,String> resBody = new HashMap<>();



    public ResponseEntity<?> createBooking(Booking bookingData, UUID userId, UUID movieId){
        try{
            User user = userRepo.findById(userId).orElse(null);
            Movie movie = movieRepo.findById(movieId).orElse(null);

            if(user==null || movie==null){
                resBody.put("message","User of Movie doesn't found");
                return new ResponseEntity<>(resBody, HttpStatus.BAD_REQUEST);
            }

            //set the userId and movieId as FK;
            bookingData.setUser(user);
            bookingData.setMovie(movie);

            //save the booking data to the DB
            bookingRepo.save(bookingData);

            //decrease seats from movie schema
            movie.setAvailableSeats(movie.getAvailableSeats()-bookingData.getSeats());
            movieRepo.save(movie);

            resBody.put("message","successfully booked");
            return new ResponseEntity<>(resBody,HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    public ResponseEntity<?> cancelBooking(UUID bookingId){
        try{
            Booking booking = bookingRepo.findById(bookingId).orElse(null);

            if(booking==null){
                resBody.put("message","Booking not found");
                return new ResponseEntity<>(resBody,HttpStatus.NOT_FOUND);
            }

            //re-update the movie schema by adding the canceled seats
            Movie movie = booking.getMovie();
            movie.setAvailableSeats(movie.getAvailableSeats()+booking.getSeats());
            movieRepo.save(movie);

            //change the status to false and save again
            booking.setBookingStatus(false);
            bookingRepo.save(booking);



            resBody.put("message","booking canceled");
            return new ResponseEntity<>(resBody,HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> getBookingsbyUserId(UUID userId){
        try{
            User user = userRepo.findById(userId).orElse(null);

            if(user==null){
                resBody.put("message","user not found");
                return new ResponseEntity<>(resBody,HttpStatus.NOT_FOUND);
            }
            List<Booking> bookings = bookingRepo.findByUserId(userId);

            return ResponseEntity.ok(bookings);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

}
