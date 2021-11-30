package com.lab1Spring.musiquorum.dtos;

import com.lab1Spring.musiquorum.models.Course;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateClassDTO {


    @NotBlank
    private String className;

    @NotNull
    private String duration;


    public CreateClassDTO(String className, String duration) {
        this.className = className;
        this.duration = duration;
    }

    public String getClassName() {
        return className;
    }

    public String getDuration() {
        return duration;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
