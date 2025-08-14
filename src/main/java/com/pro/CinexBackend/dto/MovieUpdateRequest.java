package com.pro.CinexBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieUpdateRequest {

    private String description;

    private String category;

    private String Location;

    private LocalDateTime dateTime;

    private BigDecimal price;

    private Integer totalSeats;

    private Integer availableSeats;

    private String imgUrl;
}
