package com.example.demo.service;

import com.example.demo.controller.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.SimpleDateFormat;
import java.util.Date;


import static org.mockito.ArgumentMatchers.isA;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void Given_clientDTO_When_invoke_insertClient_Then_return_nonException() {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setAddress("123");
        clientDTO.setName("cosita");
        clientDTO.setDocument(123);
        clientDTO.setEmail("asd@asd.asd");
        clientDTO.setDate_created(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Mockito.when(clientRepository.save(new Client(clientDTO.getName(), clientDTO.getAddress(), clientDTO.getDate_created(), clientDTO.getEmail() ,clientDTO.getDocument()))).thenReturn(new Client());
        String result = clientService.insertClient(clientDTO);
        Assertions.assertEquals("client added", result);
        Mockito.verify(clientRepository).save(isA(Client.class));

    }
}
