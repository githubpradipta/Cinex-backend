package com.pro.CinexBackend.controller;

import com.pro.CinexBackend.dto.MovieUpdateRequest;
import com.pro.CinexBackend.entity.Movie;
import com.pro.CinexBackend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movie")
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/{orgId}")
    public ResponseEntity<?> addMovie(@PathVariable UUID orgId, @RequestBody Movie movie){
        return movieService.AddMovie(orgId,movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findMovieById(@PathVariable UUID id){
        return movieService.getMovieById(id);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> findMovieByCategory(@PathVariable String category){
        return movieService.getMoviesByCategory(category);
    }

    @GetMapping("/organizer/{orgId}")
    public ResponseEntity<?> findMovieByOrganizerId(@PathVariable UUID orgId){
        return movieService.getMoviesByOrganizer(orgId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovieById(@PathVariable UUID id, @RequestBody MovieUpdateRequest data){
        return movieService.updateMovieById(id,data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable UUID id){
        return movieService.deleteMovieById(id);
    }

}
