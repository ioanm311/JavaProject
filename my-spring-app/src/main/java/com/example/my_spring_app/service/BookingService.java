package com.example.my_spring_app.service;

import com.example.my_spring_app.model.Booking;
import com.example.my_spring_app.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        if (!isRoomAvailable(booking.getRoom().getId(), booking.getStartDate(), booking.getEndDate())) {
            throw new IllegalArgumentException("Camera nu este disponibilă pentru perioada selectată.");
        }
        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new IllegalArgumentException("Rezervarea nu există.");
        }
        bookingRepository.deleteById(bookingId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsForUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    private boolean isRoomAvailable(Long roomId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Booking> bookings = bookingRepository.findByRoomId(roomId);
        return bookings.stream().noneMatch(booking ->
                booking.getStartDate().isBefore(endDate) && booking.getEndDate().isAfter(startDate)
        );
    }
}
