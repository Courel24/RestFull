package com.example.demo.controller;

import com.example.demo.controller.dto.PetDTO;
import com.example.demo.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class PetController {
    private PetService petService;

    @PostMapping("/pets")
    public String registerPet(@RequestBody PetDTO petDTO) {
        return petService.insertPet(petDTO);
    }

    @GetMapping("/pets")
    public String getPetsDayAgenda(@RequestParam String date) {
        return petService.getPetsByDate(date).toString();
    }

    @GetMapping("/pets/{client}")
    public String getPetsByClient(@PathVariable int client) {
        return petService.getPetsByClient(client).toString();
    }

}
