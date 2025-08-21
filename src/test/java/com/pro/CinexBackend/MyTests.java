package com.pro.CinexBackend;

import com.pro.CinexBackend.entity.Booking;
import com.pro.CinexBackend.entity.Movie;
import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.repository.MovieRepo;
import com.pro.CinexBackend.repository.UserRepo;
import com.pro.CinexBackend.service.BookingService;
import com.pro.CinexBackend.service.MovieService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
public class MyTests {
    @Autowired
    private MovieService movieService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EntityManager em;

    @Test
    void addMovie(){
        movieService.AddMovie(UUID.fromString("467b5d4d-6ada-4456-8859-b12a8a5a4239"),Movie.builder()
                .title("Jawan")
                .description("Movie of Red Chillis")
                .price(BigDecimal.valueOf(499.00))
                .category("Action")
                .location("Kolkata, South City Mall")
                .totalSeats(200)
                .availableSeats(200)
                .dateTime(LocalDateTime.now())
                .build()
        );
        System.out.println("Movie Saved");

    }

    @Test
    void bookMovie(){
        bookingService.createBooking(Booking.builder().seats(4).build(),
                UUID.fromString("467b5d4d-6ada-4456-8859-b12a8a5a4239"),
                UUID.fromString("eb736376-bfd4-4390-80d6-095c4fa32a87")
        );

        System.out.println("BOOKING DONE");
    }

    @Test
    @Transactional
    void deleteUser(){
        userRepo.deleteById(UUID.fromString("467b5d4d-6ada-4456-8859-b12a8a5a4239"));
        System.out.println("DELETED");
        em.flush();

    }
}
