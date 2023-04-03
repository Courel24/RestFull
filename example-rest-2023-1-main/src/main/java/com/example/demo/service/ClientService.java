package com.example.demo.service;

import com.example.demo.controller.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {
    ClientRepository clientRepository;

    public void insertClient(ClientDTO clientDTO){
        clientRepository.save(new Client(clientDTO.getName(), clientDTO.getAddress(), clientDTO.getDate_created(), clientDTO.getDocument()));
    }


}
