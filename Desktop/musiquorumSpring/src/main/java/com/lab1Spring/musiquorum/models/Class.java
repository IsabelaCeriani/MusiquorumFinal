package com.lab1Spring.musiquorum.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Class {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @NotBlank
    private String className;

    @NotNull
    private String duration;

    @ManyToOne
    private  Course course;

    public Course getCourse() {
        return course;
    }




    public Class(String className, String duration, Course course) {
        this.className = className;
        this.duration = duration;
        this.course = course;
    }



    public Class() {
        this.course = new Course();
    }

    public UUID getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public String getDuration() {
        return duration;
    }



    public Class(Course course) {
        this.course = course;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
