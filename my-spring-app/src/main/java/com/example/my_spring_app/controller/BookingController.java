package com.example.my_spring_app.controller;

import com.example.my_spring_app.model.Booking;
import com.example.my_spring_app.service.BookingService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Bookings", description = "Operations related to bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new booking", description = "Creates a new booking for a user.")
    public Booking createBooking(@Valid @RequestBody @Parameter(description = "Booking details") Booking booking) {
        return bookingService.createBooking(booking);
    }

    @DeleteMapping("/cancel/{id}")
    @Operation(summary = "Cancel a booking", description = "Cancels an existing booking by ID.")
    public void cancelBooking(@PathVariable @Parameter(description = "ID of the booking to cancel") Long id) {
        bookingService.cancelBooking(id);
    }

    // GET /api/bookings/user/{userId}
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get bookings for a user", description = "Fetches all bookings made by a specific user.")
    public List<Booking> getBookingsForUser(@PathVariable @Parameter(description = "ID of the user to fetch bookings for") Long userId) {
        return bookingService.getBookingsForUser(userId);
    }

    // GET /api/bookings
    @GetMapping
    @Operation(summary = "Get all bookings", description = "Fetches all bookings. Admin-only access.")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
