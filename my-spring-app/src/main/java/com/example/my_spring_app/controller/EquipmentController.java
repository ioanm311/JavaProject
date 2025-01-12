package com.example.my_spring_app.controller;

import com.example.my_spring_app.model.Equipment;
import com.example.my_spring_app.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@Tag(name = "Equipment", description = "Operations related to equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    @Operation(summary = "Create new equipment", description = "Creates a new equipment record in the system.")
    public Equipment createEquipment(@RequestBody @Parameter(description = "Equipment details") Equipment equipment) {
        return equipmentService.createEquipment(equipment);
    }

    @GetMapping
    @Operation(summary = "Get all equipment", description = "Fetches all equipment in the system.")
    public List<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get equipment by ID", description = "Fetches a specific equipment by its ID.")
    public Equipment getEquipmentById(@PathVariable @Parameter(description = "ID of the equipment to fetch") Long id) {
        return equipmentService.getEquipmentById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete equipment", description = "Deletes an equipment record from the system.")
    public void deleteEquipment(@PathVariable @Parameter(description = "ID of the equipment to delete") Long id) {
        equipmentService.deleteEquipment(id);
    }
}
