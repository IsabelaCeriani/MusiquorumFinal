package com.lab1Spring.musiquorum.dtos;

import com.lab1Spring.musiquorum.models.Tag;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class EditCourseDTO {


    private String name;

    private String description;

    private UUID professorId;

    private Boolean isActive;

    private List<String> tagsToAdd;

    private List<String> tagsToRemove;



    public EditCourseDTO(String name, String description, UUID professorId, Boolean isActive, List<String> tagsToAdd, List<String> tagsToRemove) {
        this.name = name;
        this.description = description;
        this.professorId = professorId;
        this.isActive = isActive;
        this.tagsToAdd = tagsToAdd;
        this.tagsToRemove = tagsToRemove;
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

    public List<String> getTagsToAdd() {
        return tagsToAdd;
    }

    public List<String> getTagsToRemove() {
        return tagsToRemove;
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

    public void setTagsToAdd(List<String> tagsToAdd) {
        this.tagsToAdd = tagsToAdd;
    }

    public void setTagsToRemove(List<String> tagsToRemove) {
        this.tagsToRemove = tagsToRemove;
    }

}
