package com.lab1Spring.musiquorum.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import com.lab1Spring.musiquorum.models.*;

public class CourseDTO {


    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private UUID professorId;

    @NotNull
    private Boolean isActive;

    private List<Tag> tags;

    public CourseDTO(String name, String description, UUID professorId, List<Tag> tags) {
        this.name = name;
        this.description = description;
        this.professorId = professorId;
        this.tags = tags;
    }

    public CourseDTO() {
    }

    public CourseDTO(String name, String description, UUID professorId) {
        this.name = name;
        this.description = description;
        this.professorId = professorId;
        this.isActive = true;
    }
}
