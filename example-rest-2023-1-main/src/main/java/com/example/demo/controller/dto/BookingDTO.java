package com.example.demo.controller.dto;

import lombok.*;
@Data
@AllArgsConstructor

public class BookingDTO {

    private String id;
    private String date;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }

}
