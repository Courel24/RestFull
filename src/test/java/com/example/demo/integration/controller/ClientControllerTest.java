package com.example.demo.integration.controller;

import com.example.demo.AbstractTest;
import com.example.demo.controller.dto.ClientDTO;
import com.example.demo.controller.dto.ClientResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

@ActiveProfiles("test")
public class ClientControllerTest extends AbstractTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String PATH_CLIENTS_POST = "/clients";

    @Test
    public void Given_bookData_When_invoke_registerBooking_Then_success_message() {

        ClientDTO dto = new ClientDTO();
        dto.setDocument(1235);
        dto.setName("Laura");
        dto.setAddress("Casa roja");
        dto.setDate_created(new Date());
        ResponseEntity<ClientResponseDTO> result = restTemplate.postForEntity(PATH_CLIENTS_POST, dto, ClientResponseDTO.class);
        Assertions.assertEquals("client added", result.getBody().getResponse());

    }

}
