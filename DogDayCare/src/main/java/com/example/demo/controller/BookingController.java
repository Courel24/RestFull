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
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {
    private BookingService bookingService;

    @PostMapping("/bookings")
    public BookingResponseDTO registerBooking(@RequestBody BookingDTO bookingDTO) {
        System.out.println(bookingDTO.toString());
        return new BookingResponseDTO(bookingService.insertBooking(bookingDTO));
    }

    @GetMapping("/bookings")
    public BookingListDTO getClientBookingHistory(@RequestParam int id) {
        return new BookingListDTO(bookingService.getClientBookingHistory(id)
                .stream()
                .map(BookingDTO::fromModel)
                .collect(Collectors.toList()));
    }

    @PostMapping("/delete_bookings")
    public BookingResponseDTO deleteBookingByPetIdAndDate(@RequestParam int pet_id, @RequestParam String date) {
        return new BookingResponseDTO(bookingService.FinalizeBookingByPetIdAndDate(pet_id, date));
    }

}
