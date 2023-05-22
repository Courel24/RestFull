package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "CLIENT")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Client {
    @NonNull
    private String name;

    @NonNull
    private String address;

    @NonNull
    private Date date_created;

    @NonNull
    private String email;

    @Id
    private int document;
}