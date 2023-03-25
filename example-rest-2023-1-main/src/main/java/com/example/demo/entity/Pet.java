package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PET")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@ManyToOne()
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String date_created;

    //foranea
    @NonNull
    //@ManyToOne(targetEntity = Client.class)
    private int client;
}