package com.example.demo.controller.dto;

import com.example.demo.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    private int id;
    private String name;
    private String date_created;
    private int client;

    public static PetDTO fromModel(Pet pet) {
        return new PetDTO(pet.getId(), pet.getName(), pet.getDate_created(), pet.getClient().getDocument());
    }

}
