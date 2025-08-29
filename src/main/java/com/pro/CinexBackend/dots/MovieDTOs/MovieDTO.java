package com.pro.CinexBackend.dots.MovieDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MovieDTO {
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
}
