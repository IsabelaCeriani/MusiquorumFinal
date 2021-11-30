package com.lab1Spring.musiquorum.dtos;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public class AssignmentDTO {

    private String name;

    private String instructions;



    public AssignmentDTO(String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }




    public String getInstructions() {
        return instructions;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
