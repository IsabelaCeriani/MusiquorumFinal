package com.lab1Spring.musiquorum.dtos;

import com.lab1Spring.musiquorum.models.HomeworkStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public class HomeworkCorrectionDTO {

    private UUID homeworkId;

    private String comment;

    private UUID studentId;

    private UUID assignmentId;

    private Double grade;


    public HomeworkCorrectionDTO(UUID homeworkId, String comment, UUID studentId, UUID assignmentId, Double grade) {
        this.homeworkId = homeworkId;
        this.comment = comment;
        this.studentId = studentId;
        this.assignmentId = assignmentId;
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

    public Double getGrade() {
        return grade;
    }
}
