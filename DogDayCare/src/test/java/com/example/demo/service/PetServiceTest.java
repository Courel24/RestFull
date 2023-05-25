package com.example.demo.service;

import com.example.demo.controller.dto.PetDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Pet;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private PetService petService;

    @Test
    void Given_more_than_2_pets_When_invoke_insertPet_Then_return_limit_message() {

        PetDTO petDTO = new PetDTO();
        Pet probe = new Pet();

        Mockito.when(clientRepository.getReferenceById(petDTO.getClient())).thenReturn(new Client());
        probe.setClient(clientRepository.getReferenceById(petDTO.getClient()));

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "name", "date_created");
        Mockito.when(petRepository.count(Example.of(probe, matcher))).thenReturn(2L);
        String result = petService.insertPet(petDTO);

        Assertions.assertEquals("pet cannot be added", result);
        Mockito.verify(clientRepository, Mockito.atLeastOnce()).getReferenceById(petDTO.getClient());

        Mockito.verify(petRepository).count(Example.of(probe, matcher));
    }

    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void Given_less_than_2_pets_When_invoke_insertPet_Then_return_success_message() {

        PetDTO petDTO = new PetDTO();
        petDTO.setDate_created(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        petDTO.setId(123);
        petDTO.setName("Roberto");
        petDTO.setClient(123);
        Pet probe = new Pet();
        Mockito.when(clientRepository.getReferenceById(petDTO.getClient())).thenReturn(new Client());
        probe.setClient(clientRepository.getReferenceById(petDTO.getClient()));
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "name", "date_created");
        Mockito.when(petRepository.count(Example.of(probe, matcher))).thenReturn(1L);
        Mockito.when(petRepository.save(new Pet(petDTO.getId(), petDTO.getName(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), clientRepository.getReferenceById(petDTO.getClient())))).thenReturn(new Pet());
        String result = petService.insertPet(petDTO);
        Assertions.assertEquals("pet added", result);
        Mockito.verify(clientRepository, Mockito.atLeastOnce()).getReferenceById(petDTO.getClient());
        Mockito.verify(petRepository).count(Example.of(probe, matcher));
        Mockito.verify(petRepository).save(isA(Pet.class));
    }

    @Test
    void Given_date_When_invoke_getPetsByDate_Then_return_pet_list_by_date() {

        Pet probe = new Pet();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        probe.setDate_created(date);

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "name", "Client");

        List<Pet> datePets = new ArrayList<>();
        datePets.add(probe);
        Mockito.when(petRepository.findAll(Example.of(probe, matcher))).thenReturn(datePets);
        List result = petService.getPetsByDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Assertions.assertEquals(datePets, result);
        Mockito.verify(petRepository).findAll(Example.of(probe, matcher));

    }

    @Test
    void Given_clientID_When_invoke_getPetsByDate_Then_return_pet_list_by_clientID() {

        Pet probe = new Pet();
        int clientID = 123;

        Mockito.when(clientRepository.getReferenceById(clientID)).thenReturn(new Client());
        probe.setClient(clientRepository.getReferenceById(clientID));

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "name", "date_created");

        List<Pet> clientPets = new ArrayList<>();
        clientPets.add(probe);
        Mockito.when(petRepository.findAll(Example.of(probe, matcher))).thenReturn(clientPets);
        List result = petService.getPetsByClient(clientID);
        Assertions.assertEquals(clientPets, result);
        Mockito.verify(petRepository).findAll(Example.of(probe, matcher));
        Mockito.verify(clientRepository, Mockito.atLeastOnce()).getReferenceById(clientID);

    }
}
