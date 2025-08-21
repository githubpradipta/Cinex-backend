package com.pro.CinexBackend.service;

import com.pro.CinexBackend.dto.MovieUpdateRequest;
import com.pro.CinexBackend.entity.Movie;
import com.pro.CinexBackend.entity.User;
import com.pro.CinexBackend.repository.MovieRepo;
import com.pro.CinexBackend.repository.UserRepo;
import jakarta.transaction.Transactional;
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
public class MovieService {

    private final MovieRepo movieRepo;
    private final UserRepo userRepo;
    Map<String,String> resBody = new HashMap<>();

    public ResponseEntity<?> AddMovie(UUID organizerId, Movie movie){
        try{
            //check for correct organizer id
            User organizer = userRepo.findById(organizerId).orElse(null);
            if(organizer==null){
                resBody.put("message","Organizer doesn't exists");
                return new ResponseEntity<>(resBody, HttpStatus.NOT_FOUND);

            }

            //check for duplicate titles
            Movie checkMovie = movieRepo.findByTitle(movie.getTitle());
            if(checkMovie!=null){
                resBody.put("message","Title already exists");
                return new ResponseEntity<>(resBody, HttpStatus.NOT_FOUND);
            }

            movie.setOrganizer(organizer);
            movieRepo.save(movie);

            resBody.put("message","Movie Added");
            return ResponseEntity.ok(resBody);

        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getMovieById(UUID id){
        try{
            Movie movie = movieRepo.findById(id).orElse(null);
            if(movie==null) {
                resBody.put("message","Movie not found");
                return new ResponseEntity<>(resBody,HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(movie);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //searching & filtering

    public ResponseEntity<?> getMoviesByCategory(String category){
        try{
            List<Movie> movies = movieRepo.findByCategory(category);
            return ResponseEntity.ok(movies);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getMoviesByOrganizer(UUID orgId){
        try{
            User organizer = userRepo.findById(orgId).orElse(null);
            if(organizer==null){
                resBody.put("message","organiser doesn't exists");
                return new ResponseEntity<>(resBody,HttpStatus.NOT_FOUND);
            }

            List<Movie> movies = movieRepo.findByOrganizerId(orgId);
            return ResponseEntity.ok(movies);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findMoviesBySearchQuery(String keyword){
        try {
            List<Movie> movies = movieRepo.searchMovies(keyword);
            return ResponseEntity.ok(movies);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<?> deleteMovieById(UUID id){
        try{
            Movie movie = movieRepo.findById(id).orElse(null);
            if(movie==null){
                resBody.put("message","Movie already not exists");
                return new ResponseEntity<>(resBody,HttpStatus.NOT_FOUND);
            }
            movieRepo.deleteById(id);
            resBody.put("message","Movie deleted");
            return ResponseEntity.ok(resBody);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateMovieById(UUID id, MovieUpdateRequest data){
        try{
            Movie movie = movieRepo.findById(id).orElse(null);
            if(movie==null){
                resBody.put("message","Movie doesn't exists");
                return new ResponseEntity<>(resBody,HttpStatus.NOT_FOUND);
            }

            //updates
            if(data.getDescription()!=null) movie.setDescription(data.getDescription());
            if(data.getPrice()!=null) movie.setPrice(data.getPrice());
            if(data.getTotalSeats()!=null) movie.setTotalSeats(data.getTotalSeats());
            if(data.getAvailableSeats()!=null) movie.setAvailableSeats(data.getAvailableSeats());
            if(data.getCategory()!=null) movie.setCategory(data.getCategory());
            if(data.getLocation()!=null) movie.setLocation(data.getLocation());
            if(data.getDateTime()!=null) movie.setDateTime(data.getDateTime());
            if(data.getImgUrl()!=null) movie.setImgUrl(data.getImgUrl());

            //save after updating
            Movie updatedMovie = movieRepo.save(movie);

            return ResponseEntity.ok(updatedMovie);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
