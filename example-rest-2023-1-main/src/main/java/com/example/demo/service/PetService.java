package com.example.demo.service;

import com.example.demo.controller.dto.PetDTO;
import com.example.demo.entity.Pet;
import com.example.demo.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetService {
    PetRepository petRepository;
    public String insertPet(PetDTO petDTO){
        Pet probe = new Pet();
        probe.setClient(petDTO.getClient());
        ExampleMatcher matcher = ExampleMatcher.matchingAny();
        if (petRepository.count(Example.of(probe, matcher)) < 2) {
            petRepository.save(new Pet(petDTO.getId(), petDTO.getName(), petDTO.getDate_created(), petDTO.getClient()));
            return "pet added";
        } else {
            return "pet cannot be added";
        }
    }

    public List<Pet> getPetsByDate(String date){
        Pet probe = new Pet();
        probe.setDate_created(date);
        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        System.out.println(Example.of(probe, matcher));
        return petRepository.findAll(Example.of(probe, matcher));
    }

    public List<Pet> getPetsByClient(int clientDocument){
        Pet probe = new Pet();
        probe.setClient(clientDocument);
        ExampleMatcher matcher = ExampleMatcher.matchingAny();
        return petRepository.findAll(Example.of(probe, matcher));
    }
}
