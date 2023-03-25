package com.example.demo.controller;

import com.example.demo.controller.dto.BookingDTO;
import com.example.demo.controller.dto.ClientDTO;
import com.example.demo.controller.dto.PetDTO;
import com.example.demo.entity.Client;
import com.example.demo.service.BookingService;
import com.example.demo.service.ClientService;
import com.example.demo.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class DogDaycareController {
private ClientService clientService;
private PetService petService;

private BookingService bookingService;


    @PostMapping("/clients")
    public String registerClient(@RequestBody ClientDTO clientDTO) {
        clientService.insertClient(clientDTO);
        return "client added";
    }

    @PostMapping("/pets")
    public String registerPet(@RequestBody PetDTO petDTO) {
        return petService.insertPet(petDTO);
    }

    @PostMapping("/bookings")
    public String registerBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.insertBooking(bookingDTO);
    }

    @GetMapping("/pets")
    public String getPetsDayAgenda(@RequestParam String date) {
        System.out.println(date);
        return petService.getPetsByDate(date).toString();
    }

    @GetMapping("/bookings")
    public String getClientBookingHistory(@RequestParam int id) {
        return bookingService.getClientBookingHistory(id).toString();
    }

}
