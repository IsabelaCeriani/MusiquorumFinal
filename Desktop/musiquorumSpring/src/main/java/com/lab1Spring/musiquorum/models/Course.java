package com.lab1Spring.musiquorum.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Entity
public class Course {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private UUID professorId;

    @NotNull
    private Boolean isActive;

    @ManyToMany
    private List<Tag> tags;

    @NotNull
    private String imageUrl;

    @ManyToMany
    private List<User> enrolledUsers;



    public Course(String name, String description, UUID professorId, List<Tag> tags) {
        this.name = name;
        this.description = description;
        this.professorId = professorId;
        this.tags = tags;
        this.imageUrl = "";
        isActive = true;
    }

    public Course(String name, String description, UUID professorId,String imgUrl) {
        this.name = name;
        this.description = description;
        this.professorId = professorId;
        this.imageUrl = imgUrl;
        isActive = true;
    }



    public Course() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getProfessorId() {
        return professorId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<User> getEnrolledUsers() {
        return enrolledUsers;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProfessorId(UUID professorId) {
        this.professorId = professorId;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setEnrolledUsers(List<User> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }



}
