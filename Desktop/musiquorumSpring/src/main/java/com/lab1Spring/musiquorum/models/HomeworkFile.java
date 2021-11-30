package com.lab1Spring.musiquorum.models;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class HomeworkFile {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    private String fileName;

    private String fileType;

    private Date dateUploaded;

    @ManyToOne
    private Homework homework;


    @Lob
    private byte[] data;

    public HomeworkFile() {
    }

    public HomeworkFile(String fileName, String fileType, Date dateUploaded, Homework homework, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.dateUploaded = dateUploaded;
        this.homework = homework;
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

    public void setHomework(Homework homework) {
        this.homework = homework;
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

    public Homework getHomework() {
        return homework;
    }

    public byte[] getData() {
        return data;
    }
}
