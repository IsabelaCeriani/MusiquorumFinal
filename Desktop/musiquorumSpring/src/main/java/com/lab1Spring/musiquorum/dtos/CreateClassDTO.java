package com.lab1Spring.musiquorum.dtos;

import com.lab1Spring.musiquorum.models.Course;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateClassDTO {


    @NotBlank
    private String className;

    @NotNull
    private int duration;


    public CreateClassDTO(String className, int duration) {
        this.className = className;
        this.duration = duration;
    }

    public String getClassName() {
        return className;
    }

    public int getDuration() {
        return duration;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
