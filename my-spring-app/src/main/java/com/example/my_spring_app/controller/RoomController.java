package com.example.my_spring_app.controller;

import com.example.my_spring_app.model.Room;
import com.example.my_spring_app.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@Tag(name = "Rooms", description = "Operations related to rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    @Operation(summary = "Get all rooms", description = "Fetches all rooms available in the system.")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new room", description = "Adds a new room to the system (admin only).")
    public Room addRoom(@RequestBody @Parameter(description = "Room object to be added") Room room) {
        if (room.getCategory() == null || room.getCategory().getId() == null) {
            throw new IllegalArgumentException("Category must be provided.");
        }
        return roomService.addRoom(room);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a room", description = "Deletes a room from the system (admin only).")
    public void deleteRoom(@PathVariable @Parameter(description = "ID of the room to be deleted") Long id) {
        roomService.deleteRoom(id);
    }
}
