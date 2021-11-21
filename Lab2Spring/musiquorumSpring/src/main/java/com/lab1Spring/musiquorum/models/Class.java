package com.lab1Spring.musiquorum.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Class {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;


    @ManyToOne
    private Course course;
}
