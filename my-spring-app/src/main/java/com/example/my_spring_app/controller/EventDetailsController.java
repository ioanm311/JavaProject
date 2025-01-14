package com.example.my_spring_app.controller;

import com.example.my_spring_app.model.EventDetails;
import com.example.my_spring_app.service.EventDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventdetails")
@Tag(name = "EventDetails", description = "Operations related to event details")
public class EventDetailsController {

    @Autowired
    private EventDetailsService eventDetailsService;

    @PostMapping
    @Operation(summary = "Create event details", description = "Creates new event details in the system.")
    public EventDetails createEventDetails(@RequestBody @Parameter(description = "Event details to be created") EventDetails eventDetails) {
        return eventDetailsService.createEventDetails(eventDetails);
    }

    @GetMapping
    @Operation(summary = "Get all event details", description = "Fetches all event details from the system.")
    public List<EventDetails> getAllEventDetails() {
        return eventDetailsService.getAllEventDetails();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get event details by ID", description = "Fetches specific event details by its ID.")
    public ResponseEntity<EventDetails> getEventDetailsById(@PathVariable @Parameter(description = "ID of the event details to fetch") Long id) {
        try {
            EventDetails eventDetails = eventDetailsService.getEventDetailsById(id);
            return new ResponseEntity<>(eventDetails, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // Dacă nu găsim EventDetails, returnăm 404
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete event details", description = "Deletes event details from the system.")
    public void deleteEventDetails(@PathVariable @Parameter(description = "ID of the event details to delete") Long id) {
        eventDetailsService.deleteEventDetails(id);
    }
}
