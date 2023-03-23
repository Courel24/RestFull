package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "PET")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(cascade = CascadeType.ALL)
    private int id;

    private String name;

    private String dateCreated;

    //foranea
    @ManyToOne
    @JoinColumn(name = "Client")
    private int client;
}