package com.lab1Spring.musiquorum.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.lab1Spring.musiquorum.models.*;

public class CreateCourseDTO {

    private UUID eventId;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private UUID professorId;

    @NotBlank
    private String imageUrl;

//    @NotNull
//    private Boolean isActive;

    @NotNull
    private List<Tag> tags;

    public CreateCourseDTO(UUID eventId, String name, String description, UUID professorId, List<Tag> tags, String img) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.professorId = professorId;
        this.tags = tags;
        this.imageUrl = img;
    }


    public String getImg() {
        return imageUrl;
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

    public List<Tag> getTags() {
        return tags;
    }

    public UUID getEventId() {
        return eventId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}


