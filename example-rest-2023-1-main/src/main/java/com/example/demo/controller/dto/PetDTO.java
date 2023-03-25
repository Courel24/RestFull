package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PetDTO {
    private int id;
    private String name;
    private String date_created;
    private int client;
}
