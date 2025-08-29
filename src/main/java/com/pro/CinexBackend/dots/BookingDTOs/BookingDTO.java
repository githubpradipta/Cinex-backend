package com.pro.CinexBackend.dots.BookingDTOs;

import lombok.Data;

import java.util.UUID;

@Data
public class BookingDTO {
    private UUID id;
    private UUID movieId;
    private String movieName;
    private UUID userId;
    private Boolean status;
    private Integer seats;
}
