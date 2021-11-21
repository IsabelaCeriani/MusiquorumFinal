package com.lab1Spring.musiquorum.controllers;

import com.lab1Spring.musiquorum.dtos.*;
import com.lab1Spring.musiquorum.models.Course;
import com.lab1Spring.musiquorum.models.User;
import com.lab1Spring.musiquorum.services.CourseService;
import com.lab1Spring.musiquorum.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("courses")
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping("/login")
    public ResponseEntity<CourseDTO> login(@RequestBody @Valid  CreateCourseDTO courseDTO){
        return ResponseEntity.ok(courseService.addCourse(courseDTO));
    }

    @PutMapping("/editCourse")
    public ResponseEntity<CourseDTO> editCourse(@RequestBody @Valid CreateCourseDTO courseDTO){
        return ResponseEntity.ok(courseService.editCourse(courseDTO));
    }

    @DeleteMapping("/deleteCorse/{eventId}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable @Valid UUID eventId){
        return ResponseEntity.ok(courseService.deleteCourse(eventId));
    }

    @GetMapping
    public  ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping
    public  ResponseEntity<List<Course>> getMyProfCourses(){
        return ResponseEntity.ok(courseService.getTeacherCourses());
    }


}
