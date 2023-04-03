package com.example.demo.controller.dto;

import lombok.*;

@Data
public class BookingDTO {
    private int id;
    private int client_id;
    private int pet_id;
    private String date;
}
