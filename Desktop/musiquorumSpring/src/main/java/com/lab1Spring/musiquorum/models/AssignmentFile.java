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

    public AssignmentFile() {
    }

    public AssignmentFile(String fileName, String fileType, Date dateUploaded, Assignment assignment, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.dateUploaded = dateUploaded;
        this.assignment = assignment;
        this.data = data;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setDateUploaded(Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public Date getDateUploaded() {
        return dateUploaded;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public byte[] getData() {
        return data;
    }
}
