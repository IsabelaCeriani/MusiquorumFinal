package com.lab1Spring.musiquorum.controllers;

import com.lab1Spring.musiquorum.dtos.HomeworkCorrectionDTO;
import com.lab1Spring.musiquorum.dtos.HomeworkDTO;
import com.lab1Spring.musiquorum.models.Homework;
import com.lab1Spring.musiquorum.services.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("homerworks")
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;


    @GetMapping("/{homeworkId}")
    public Homework getHomework(@PathVariable UUID homeworkId) {
        return homeworkService.getHomework(homeworkId);
    }

    @PostMapping("")
    public Homework handIn(@RequestBody HomeworkDTO homework) {
        return homeworkService.createHomework(homework);
    }

    @PutMapping("")
    public Homework updateHomework(@RequestBody HomeworkCorrectionDTO homeworkDTO) {
        return homeworkService.rateHomework(homeworkDTO);
    }
}
