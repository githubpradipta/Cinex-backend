package com.pro.CinexBackend.controller;

import com.pro.CinexBackend.dots.MovieDTOs.MovieUpdateRequest;
import com.pro.CinexBackend.entity.Movie;
import com.pro.CinexBackend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@EnableMethodSecurity(prePostEnabled = true)
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;


    @PreAuthorize("hasAuthority('ORGANIZER')")
    @PostMapping("/{orgId}")
    public ResponseEntity<?> addMovie(@PathVariable UUID orgId, @RequestBody Movie movie){
        return movieService.AddMovie(orgId,movie);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ORGANIZER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findMovieById(@PathVariable UUID id){
        return movieService.getMovieById(id);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ORGANIZER')")
    @GetMapping("/category/{category}")
    public ResponseEntity<?> findMovieByCategory(@PathVariable String category){
        return movieService.getMoviesByCategory(category);
    }

    @PreAuthorize("hasAuthority('ORGANIZER')")
    @GetMapping("/organizer/{orgId}")
    public ResponseEntity<?> findMovieByOrganizerId(@PathVariable UUID orgId){
        return movieService.getMoviesByOrganizer(orgId);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ORGANIZER')")
    @GetMapping("/search")
    public ResponseEntity<?> findMoviesBySearch(@RequestParam("q") String query){
        return movieService.findMoviesBySearchQuery(query);
    }

    @PreAuthorize("hasAuthority('ORGANIZER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovieById(@PathVariable UUID id, @RequestBody MovieUpdateRequest data){
        return movieService.updateMovieById(id,data);
    }

    @PreAuthorize("hasAuthority('ORGANIZER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable UUID id){
        return movieService.deleteMovieById(id);
    }

}
