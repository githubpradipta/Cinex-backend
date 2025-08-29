package com.pro.CinexBackend.dots.OrganizerDTOs;

import com.pro.CinexBackend.dots.MovieDTOs.MovieDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerDTO {
    private UUID id;
    private String name;
    private String email;
}
