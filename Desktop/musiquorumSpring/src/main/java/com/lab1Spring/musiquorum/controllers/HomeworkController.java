package com.lab1Spring.musiquorum.controllers;

import com.lab1Spring.musiquorum.dtos.HomeworkCorrectionDTO;
import com.lab1Spring.musiquorum.dtos.HomeworkDTO;
import com.lab1Spring.musiquorum.models.Homework;
import com.lab1Spring.musiquorum.models.HomeworkFile;
import com.lab1Spring.musiquorum.services.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/downloadHomework/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable UUID fileId) {
        // Load file from database
        HomeworkFile dbhomeworkFile = homeworkService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbhomeworkFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbhomeworkFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbhomeworkFile.getData()));
    }

    @PostMapping("")
    public Homework handIn(@RequestBody HomeworkDTO homework) {
        return homeworkService.handInHomework(homework);
    }

    @PutMapping("")
    public Homework updateHomework(@RequestBody HomeworkCorrectionDTO homeworkDTO) {
        return homeworkService.rateHomework(homeworkDTO);
    }
}
