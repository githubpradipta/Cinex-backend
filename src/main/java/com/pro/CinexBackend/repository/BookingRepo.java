package com.pro.CinexBackend.repository;

import com.pro.CinexBackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepo extends JpaRepository<Booking, UUID> {
    List<Booking> findByUserId(UUID userId);
}
