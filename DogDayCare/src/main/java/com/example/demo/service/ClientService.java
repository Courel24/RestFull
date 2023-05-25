package com.example.demo.service;

import com.example.demo.controller.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;

    public String insertClient(ClientDTO clientDTO) {
        clientRepository.save(new Client(clientDTO.getName(), clientDTO.getAddress(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), clientDTO.getEmail(), clientDTO.getDocument()));
        return "client added";
    }


}
