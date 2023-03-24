package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class PetDTO {
    private int id;
    private String name;
    private String dateCreated;

    private int client;

    @Override
    public String toString() {
        return "PetDTO{" +
                "id=" + id +
                ", name='" + name +
                ", dateCreated='" + dateCreated +
                ", client=" + client +
                '}';
    }
}
