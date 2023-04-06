package com.example.demo.controller;

import com.example.demo.controller.dto.BookingDTO;
import com.example.demo.controller.dto.BookingListDTO;
import com.example.demo.controller.dto.BookingResponseDTO;
import com.example.demo.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class BookingController {
    private BookingService bookingService;

    @PostMapping("/bookings")
    public BookingResponseDTO registerBooking(@RequestBody BookingDTO bookingDTO) {
        return new BookingResponseDTO(bookingService.insertBooking(bookingDTO));
    }

    @GetMapping("/bookings")
    public BookingListDTO getClientBookingHistory(@RequestParam int id) {
        //return bookingService.getClientBookingHistory(id);
        return new BookingListDTO(bookingService.getClientBookingHistory(id)
                .stream()
                .map(BookingDTO::fromModel)
                .collect(Collectors.toList()));
    }

}
