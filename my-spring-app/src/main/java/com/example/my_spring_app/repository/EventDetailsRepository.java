package com.example.my_spring_app.repository;

import com.example.my_spring_app.model.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDetailsRepository extends JpaRepository<EventDetails, Long> {
}