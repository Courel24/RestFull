package test.java.com.example.demo.integration.controller;

import test.java.com.example.demo.AbstractTest;
import com.example.demo.controller.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.text.SimpleDateFormat;
import java.util.Date;

@ActiveProfiles("test")
public class PetControllerTest extends AbstractTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String PATH_PETS_GET_BY_DATE = "/pets?date=";
    private static final String PATH_PETS_GET_BY_CLIENT = "/pets/{client}";
    private static final String PATH_PETS_POST = "/pets";

    @Test
    public void Given_petData_When_invoke_registerPet_Then_success_message() {

        PetDTO dto = new PetDTO();
        dto.setId(1);
        dto.setName("Oswe");
        dto.setClient(123);
        dto.setDate_created(new Date());
        dto.setDate_created(new Date());
        ResponseEntity<PetResponseDTO> result = restTemplate.postForEntity(PATH_PETS_POST, dto, PetResponseDTO.class);
        Assertions.assertEquals("pet added", result.getBody().getResponse());

    }

    @Test
    public void Given_date_When_invoke_getPetsDayAgenda_Then_pet_list_size_two() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        ResponseEntity<PetListDTO> result = restTemplate.getForEntity(PATH_PETS_GET_BY_DATE + date, PetListDTO.class);

        Assertions.assertEquals(3, result.getBody().getPetList().size());

    }

    @Test
    public void Given_clientID_When_invoke_getPetsByClient_Then_pet_list_size_one() {
        ResponseEntity<PetListDTO> result = restTemplate.exchange(PATH_PETS_GET_BY_CLIENT, HttpMethod.GET, HttpEntity.EMPTY, PetListDTO.class, 123);
        Assertions.assertEquals(1, result.getBody().getPetList().size());

    }

}
