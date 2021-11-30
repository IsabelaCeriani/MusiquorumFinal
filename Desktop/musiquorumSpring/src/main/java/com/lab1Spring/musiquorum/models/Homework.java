package com.lab1Spring.musiquorum.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Homework {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    private String comment;

    @NotNull
    private UUID userid;

    @ManyToOne
    private Assignment assignment;



    private Double grade;

    private HomeworkStatus status;

    public Homework() {
    }



    public Homework(UUID userid, Assignment assignment, HomeworkStatus status) {
        this.userid = userid;
        this.assignment = assignment;
        this.status = status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }


    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public void setStatus(HomeworkStatus status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public UUID getUserid() {
        return userid;
    }

    public Assignment getAssignment() {
        return assignment;
    }


    public Double getGrade() {
        return grade;
    }

    public HomeworkStatus getStatus() {
        return status;
    }
}
