package com.lab1Spring.musiquorum.dtos;

import com.lab1Spring.musiquorum.models.Assignment;

import java.util.UUID;

public class PendingAssignmentDTO {

    UUID courseId;
    String courseName;
    Assignment assignment;

    public PendingAssignmentDTO(UUID courseId, String courseName, Assignment assignment) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.assignment = assignment;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public Assignment getAssignment() {
        return assignment;
    }
}
