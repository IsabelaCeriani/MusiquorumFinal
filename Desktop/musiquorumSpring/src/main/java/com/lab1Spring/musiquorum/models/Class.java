package com.lab1Spring.musiquorum.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Class {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @NotBlank
    private String className;

    @NotNull
    private int duration;

//    @OneToMany(orphanRemoval=true)
//    @JoinColumn(name="CLASS_ID") // como una clase tiene mucho material, el id de la clase debe estar en la tabla de material
//    private Set<Material> materials = new HashSet<>();


//    @OneToMany(orphanRemoval=true)
//    @JoinColumn(name="CLASS_ID") // como un curso tiene muchas entregas, el id del curso debe estar en la tabla de entrega
//    private Set<Assignment> assignments = new HashSet<>();

//    @Column(name = "FILENAME")
//    private String fileName;

    @ManyToOne
    private Course course;

    public Class() {
    }
}
