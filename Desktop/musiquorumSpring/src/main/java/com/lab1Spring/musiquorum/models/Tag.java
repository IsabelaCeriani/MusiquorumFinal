package com.lab1Spring.musiquorum.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Tag {


    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @Column(unique = true)
    private String name;

    @ManyToMany
    private List<Course> course;

    public Tag(String name) {
        this.name = name;
    }


    public Tag() {

    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourse() {
        return course;
    }
}
