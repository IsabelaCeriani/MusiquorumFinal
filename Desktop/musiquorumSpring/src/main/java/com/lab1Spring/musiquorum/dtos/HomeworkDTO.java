package com.lab1Spring.musiquorum.dtos;

import com.lab1Spring.musiquorum.models.Assignment;
import com.lab1Spring.musiquorum.models.HomeworkStatus;
import com.lab1Spring.musiquorum.responses.UploadFileResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HomeworkDTO {


    private UUID homeworkId;

    private String comment;

    private UUID studentId;

    private UUID assignmentId;

    List<MultipartFile> files;
    private Double grade;


    public HomeworkDTO(UUID homeworkId, String comment, UUID studentId, UUID assignmentId, List<MultipartFile> files, Double grade) {
        this.homeworkId = homeworkId;
        this.comment = comment;
        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.files = files;
        this.grade = grade;
    }

    public void setHomeworkId(UUID homeworkId) {
        this.homeworkId = homeworkId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public void setAssignmentId(UUID assignmentId) {
        this.assignmentId = assignmentId;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }



    public UUID getHomeworkId() {
        return homeworkId;
    }

    public String getComment() {
        return comment;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public UUID getAssignmentId() {
        return assignmentId;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public Double getGrade() {
        return grade;
    }


}
