package com.lab1Spring.musiquorum.models;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Assignment {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @ManyToOne
    private Class class_;

    @ManyToOne
    private User student;

    private String fileName;

    private String fileType;

    private Date dateUploaded;



    @Lob
    private byte[] data;

    public Assignment() {

    }
}
