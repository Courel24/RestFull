package com.example.demo.controller;

import com.example.demo.controller.dto.BookingDTO;
import com.example.demo.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class BookingController {
    private BookingService bookingService;

    @PostMapping("/bookings")
    public String registerBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.insertBooking(bookingDTO);
    }

    @GetMapping("/bookings")
    public String getClientBookingHistory(@RequestParam int id) {
        return bookingService.getClientBookingHistory(id).toString();
    }

}
