package com.example.demo.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PetDTO {
    private int id;
    private String name;
    private Date date_created;
    private int client;
}
