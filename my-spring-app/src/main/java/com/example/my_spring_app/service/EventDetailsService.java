package com.example.my_spring_app.service;

import com.example.my_spring_app.model.Booking;
import com.example.my_spring_app.model.EventDetails;
import com.example.my_spring_app.repository.BookingRepository;
import com.example.my_spring_app.repository.EventDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventDetailsService {

    @Autowired
    private EventDetailsRepository eventDetailsRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public EventDetails createEventDetails(EventDetails eventDetails) {
        Optional<Booking> booking = bookingRepository.findById(eventDetails.getBooking().getId());
        if (booking.isPresent()) {
            eventDetails.setBooking(booking.get());
            return eventDetailsRepository.save(eventDetails);
        } else {
            throw new RuntimeException("Booking not found with id: " + eventDetails.getBooking().getId());
        }
    }

    public List<EventDetails> getAllEventDetails() {
        return eventDetailsRepository.findAll();
    }

    public Optional<EventDetails> getEventDetailsById(Long id) {
        return eventDetailsRepository.findById(id);
    }

    public void deleteEventDetails(Long id) {
        eventDetailsRepository.deleteById(id);
    }
}
