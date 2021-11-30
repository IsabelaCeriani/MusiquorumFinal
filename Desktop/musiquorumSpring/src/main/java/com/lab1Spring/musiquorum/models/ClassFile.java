package com.lab1Spring.musiquorum.models;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class ClassFile {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String fileName;

    private String fileType;

    private Date dateUploaded;


    @Lob
    private byte[] data;

    @ManyToOne
    private Class classs;



    public ClassFile() {

    }

    public ClassFile(String fileName, String fileType, Date dateUploaded, byte[] data, Class class_) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.dateUploaded = dateUploaded;
        this.data = data;
        this.classs = class_;
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

    public byte[] getData() {
        return data;
    }

    public Class getClasss() {
        return classs;
    }

}
