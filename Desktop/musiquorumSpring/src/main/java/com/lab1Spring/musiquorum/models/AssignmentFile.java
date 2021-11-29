package com.lab1Spring.musiquorum.models;

import com.lab1Spring.musiquorum.models.Assignment;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class AssignmentFile {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    private String fileName;

    private String fileType;

    private Date dateUploaded;

    @ManyToOne
    private Assignment assignment;


    @Lob
    private byte[] data;

    public AssignmentFile(String fileName, String fileType, Date dateUploaded, Assignment assignment, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.dateUploaded = dateUploaded;
        this.assignment = assignment;
        this.data = data;
    }
}
