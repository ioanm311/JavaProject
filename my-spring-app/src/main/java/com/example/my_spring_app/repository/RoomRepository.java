package com.example.my_spring_app.repository;

import com.example.my_spring_app.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
