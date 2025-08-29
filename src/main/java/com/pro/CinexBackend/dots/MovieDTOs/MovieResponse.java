package com.pro.CinexBackend.dots.MovieDTOs;

import com.pro.CinexBackend.dots.OrganizerDTOs.OrganizerDTO;
import com.pro.CinexBackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private UUID id;
    private String title;
    private String description;
    private String category;
    private String location;
    private LocalDateTime dateTime;
    private BigDecimal price;
    private Integer totalSeats;
    private Integer availableSeats;
    private String imgUrl;

    //organizer details
    private OrganizerDTO organizer;
}
