package com.lab1Spring.musiquorum.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Homework {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    private String comment;

    @NotNull
    private UUID userid;

    @NotNull
    private UUID assignmentId;

    private UUID fileid;


}
