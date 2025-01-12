package com.example.my_spring_app.repository;

import com.example.my_spring_app.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByRoomId(Long roomId); // pt a verifica disponibilitatea camerei
}
