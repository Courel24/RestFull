package com.example.demo.controller.dto;

import lombok.*;

import java.util.Date;

@Data
public class ClientDTO {
    private String name;
    private String address;
    private Date date_created;

    private String email;
    private int document;
}
