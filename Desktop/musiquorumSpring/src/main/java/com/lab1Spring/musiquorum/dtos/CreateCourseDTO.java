package com.lab1Spring.musiquorum.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.lab1Spring.musiquorum.models.*;

public class CreateCourseDTO {



    private UUID id;


    @NotBlank
    private String name;



    @NotBlank
    private String description;


    private String imageUrl;

//    @NotNull
//    private Boolean isActive;

    private List<String> tags;

    public CreateCourseDTO(String name, String description, List<String> tags, String img) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.imageUrl = img;
    }
    public CreateCourseDTO(UUID id, String name, String description, List<String> tags, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.imageUrl = img;
    }

    public CreateCourseDTO() {
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


    public List<String> getTags() {
        return tags;
    }


    public String getImageUrl() {
        return imageUrl;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }



    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }
}


