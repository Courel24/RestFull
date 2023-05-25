package com.example.demo.controller;

import com.example.demo.controller.dto.PetDTO;
import com.example.demo.controller.dto.PetListDTO;
import com.example.demo.controller.dto.PetResponseDTO;
import com.example.demo.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PetController {
    private PetService petService;

    @PostMapping("/pets")
    public PetResponseDTO registerPet(@RequestBody PetDTO petDTO) {
        return new PetResponseDTO(petService.insertPet(petDTO));
    }

    @GetMapping("/pets")
    public PetListDTO getPetsDayAgenda(@RequestParam String date) throws ParseException {
        return new PetListDTO(
                petService.getPetsByDate(date)
                        .stream()
                        .map(PetDTO::fromModel)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/pets/{client}")
    public PetListDTO getPetsByClient(@PathVariable int client) {
        return new PetListDTO(
                petService.getPetsByClient(client)
                        .stream()
                        .map(PetDTO::fromModel)
                        .collect(Collectors.toList())
        );
    }

}
