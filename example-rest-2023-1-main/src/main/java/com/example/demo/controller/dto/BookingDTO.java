package com.example.demo.controller.dto;

import lombok.*;
@Data
@AllArgsConstructor

public class BookingDTO {

    private int id;

    private int clientId;

    private int petId;
    private String date;

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", petId=" + petId +
                ", date='" + date +
                '}';
    }

}
