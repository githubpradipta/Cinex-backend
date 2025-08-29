package com.pro.CinexBackend.dots.OrganizerDTOs;

import com.pro.CinexBackend.dots.MovieDTOs.MovieDTO;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrganizerResponse {
    private UUID id;
    private String name;
    private String email;
    private List<MovieDTO> movies;
}
