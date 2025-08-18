package com.pro.CinexBackend.repository;

import com.pro.CinexBackend.entity.Movie;
import com.pro.CinexBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MovieRepo extends JpaRepository<Movie, UUID> {
    List<Movie> findByCategory(String category);

    List<Movie> findByOrganizerId(UUID orgId);

    Movie findByTitle(String title);

    @Query("SELECT m FROM Movie m " +
            "WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "   OR LOWER(m.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Movie> searchMovies(@Param("keyword") String keyword);
}
