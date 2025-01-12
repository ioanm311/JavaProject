package com.example.my_spring_app.controller;

import com.example.my_spring_app.model.Booking;
import com.example.my_spring_app.repository.BookingRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void testCreateBooking() throws Exception {
        bookingRepository.deleteAll();

        String bookingJson = """
            {
                "eventName": "Sample Event",
                "user": {"id": 2},
                "room": {"id": 3},
                "startDate": "2025-01-15T10:00:00",
                "endDate": "2025-01-15T12:00:00"
            }
        """;

        // post
        mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventName").value("Sample Event"));

        // rezervare salvata
        Booking savedBooking = bookingRepository.findAll().stream().findFirst().orElse(null);
        assertNotNull(savedBooking);
        assertEquals("Sample Event", savedBooking.getEventName());
        assertEquals(2L, savedBooking.getUser().getId());
        assertEquals(3L, savedBooking.getRoom().getId());
    }

    @Test
    public void testCancelBooking() throws Exception {
        // exista rezervare cu id ul 10 ?
        Booking booking = bookingRepository.findById(10L).orElse(null);

        Long bookingId = booking.getId();
        System.out.println("Booking ID: " + bookingId); // Verifică ID-ul rezervării

        // delete
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/bookings/cancel/" + bookingId))
                .andExpect(status().isOk());

        Booking deletedBooking = bookingRepository.findById(bookingId).orElse(null);
        assertEquals(null, deletedBooking);
    }
}
