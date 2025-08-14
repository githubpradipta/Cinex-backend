package com.pro.CinexBackend.repository;

import com.pro.CinexBackend.entity.Movie;
import com.pro.CinexBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MovieRepo extends JpaRepository<Movie, UUID> {
    List<Movie> findByCategory(String category);

    List<Movie> findByOrganizerId(UUID orgId);

    Movie findByTitle(String title);
}
