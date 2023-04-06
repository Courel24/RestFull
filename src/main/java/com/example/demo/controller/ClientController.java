package com.example.demo.controller;

import com.example.demo.controller.dto.ClientDTO;
import com.example.demo.controller.dto.ClientResponseDTO;
import com.example.demo.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class ClientController {
    private ClientService clientService;

    @PostMapping("/clients")
    public ClientResponseDTO registerClient(@RequestBody ClientDTO clientDTO) {
        return new ClientResponseDTO(clientService.insertClient(clientDTO));

    }

}
