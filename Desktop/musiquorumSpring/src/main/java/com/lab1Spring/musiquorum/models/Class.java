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
    private int duration;

    @ManyToOne
    private final Course course;




    public Class(String className, int duration, Course course) {
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

    public int getDuration() {
        return duration;
    }

    public Course getCourse() {
        return course;
    }

    public Class(Course course) {
        this.course = course;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


}
