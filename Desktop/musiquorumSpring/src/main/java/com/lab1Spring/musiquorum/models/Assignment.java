package com.lab1Spring.musiquorum.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Assignment {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @ManyToOne
    private Class class_;


    public Assignment() {

    }
}
