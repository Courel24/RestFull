package com.example.demo.controller.dto;

import lombok.*;
@Data
@AllArgsConstructor

public class ClientDTO {

    private String id;
    private String name;
    private String address;
    private String petName;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                ", petName=" + petName +
                '}';
    }

}
