package com.example.demo.service;

import com.example.demo.controller.dto.PetDTO;
import com.example.demo.entity.Pet;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PetService {
    private PetRepository petRepository;
    private ClientRepository clientRepository;

    public String insertPet(PetDTO petDTO) {
        Pet probe = new Pet();
        probe.setClient(clientRepository.getReferenceById(petDTO.getClient()));
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "name", "date_created");
        if (petRepository.count(Example.of(probe, matcher)) < 2) {
            petRepository.save(new Pet(petDTO.getId(), petDTO.getName(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), clientRepository.getReferenceById(petDTO.getClient())));
            return "pet added";
        } else {
            return "pet cannot be added";
        }
    }

    public List<Pet> getPetsByDate(String date) {
        Pet probe = new Pet();
        probe.setDate_created(date);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "name", "Client");

        return petRepository.findAll(Example.of(probe, matcher));
    }

    public List<Pet> getPetsByClient(int clientDocument) {
        Pet probe = new Pet();
        probe.setClient(clientRepository.getReferenceById(clientDocument));
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "name", "date_created");
        return petRepository.findAll(Example.of(probe, matcher));
    }
}
