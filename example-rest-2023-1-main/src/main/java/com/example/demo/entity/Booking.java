package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BOOKING")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    //@ManyToOne(targetEntity = Client.class)
    private int client_id;

    @NonNull
    //@ManyToOne(targetEntity = Pet.class)
    private int pet_id;

    @NonNull
    private String date;
}