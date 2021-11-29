package com.lab1Spring.musiquorum.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Assignment {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @NotNull
    private String name;

    @ManyToOne
    private Class class_;

    private String instructions;

//    @ManyToOne
//    private User student;




    public Assignment() {

    }

    public Assignment(String name, Class class_, String instructions) {
        this.name = name;
        this.class_ = class_;
        this.instructions = instructions;

    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClass_(Class class_) {
        this.class_ = class_;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Class getClass_() {
        return class_;
    }

    public String getInstructions() {
        return instructions;
    }

}
