package com.example.demo.controller;


import com.example.demo.controller.dto.BookingDTO;
import com.example.demo.controller.dto.ClientDTO;
import com.example.demo.service.DbService;
import org.springframework.web.bind.annotation.*;

@RestController
public class DogDaycareController {

    private DbService service = new DbService(new MySQL());

    public DogDaycareController() {

    }

    @PostMapping("/clients")
    @ResponseBody
    public String registerClient(@RequestBody ClientDTO clientDTO) {
        return service.addClient(clientDTO);
    }

    @PostMapping("/bookings")
    @ResponseBody
    public String registerBooking(@RequestBody BookingDTO bookingDTO) {
        return service.addBooking(bookingDTO);
    }

    @GetMapping("/pets")
    public String getPetsDayAgenda(@RequestParam String date) {
        return service.getPetsByDate(date).toString();
    }

    @GetMapping("/bookings")
    public String getClientBookingHistory(@RequestParam String id) {
        return service.getClientBookingHistory(id).toString();
    }

}
