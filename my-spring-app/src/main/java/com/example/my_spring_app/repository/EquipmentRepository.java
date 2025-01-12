package com.example.my_spring_app.repository;

import com.example.my_spring_app.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}