package com.example.demo.controller.dto;

import lombok.*;
@Data
@AllArgsConstructor

public class ClientDTO {

    private String name;
    private String address;

    private String dateCreated;
    private int id;

    @Override
    public String toString() {
        return "ClientDTO{" +
                "name='" + name +
                ", address='" + address +
                ", dateCreated='" + dateCreated +
                ", id=" + id +
                '}';
    }
}
