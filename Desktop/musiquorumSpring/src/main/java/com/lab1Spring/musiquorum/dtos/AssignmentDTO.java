package com.lab1Spring.musiquorum.dtos;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public class AssignmentDTO {

    private String name;

    private String instructions;


    private List<MultipartFile> files;

    public AssignmentDTO(String name, String instructions, List<MultipartFile> files) {
        this.name = name;
        this.instructions = instructions;
        this.files = files;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }



    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getInstructions() {
        return instructions;
    }



    public List<MultipartFile> getFiles() {
        return files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
