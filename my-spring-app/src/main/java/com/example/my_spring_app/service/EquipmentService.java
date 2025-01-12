package com.example.my_spring_app.service;

import com.example.my_spring_app.model.Equipment;
import com.example.my_spring_app.model.Room;
import com.example.my_spring_app.repository.EquipmentRepository;
import com.example.my_spring_app.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Equipment createEquipment(Equipment equipment) {
        if (equipment.getRoom() == null || equipment.getRoom().getId() == null) {
            throw new RuntimeException("Room id is required");
        }

        Optional<Room> roomOpt = roomRepository.findById(equipment.getRoom().getId());
        if (roomOpt.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        // se seteaza camera pentru echipament
        equipment.setRoom(roomOpt.get());
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepository.findById(id);
    }

    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }
}
