package com.lab1Spring.musiquorum.controllers;

import com.lab1Spring.musiquorum.dtos.AssignmentDTO;
import com.lab1Spring.musiquorum.dtos.CreateClassDTO;
import com.lab1Spring.musiquorum.models.Assignment;
import com.lab1Spring.musiquorum.models.Class;
import com.lab1Spring.musiquorum.responses.UploadFileResponse;
import com.lab1Spring.musiquorum.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;


    @GetMapping("/{classId}")
    public ResponseEntity<Class> getClass(@PathVariable UUID classId) {
        return ResponseEntity.ok(classService.getClassById(classId));
    }

    @PutMapping("/{classId}")
    public ResponseEntity<Class> updateClass(@Valid @PathVariable UUID classId, @Valid @RequestBody CreateClassDTO classDTO) {
        return ResponseEntity.ok(classService.updateClass(classDTO, classId));
    }
    @PostMapping("/{courseId}")
    public ResponseEntity<CreateClassDTO> addClass(@RequestBody @Valid CreateClassDTO classDTO, @PathVariable @Valid UUID courseId) {
        return ResponseEntity.ok(classService.addClass(classDTO, courseId));
    }

//    @PostMapping("/{classId}")
//    public ResponseEntity<CreateClassDTO> addMaterialToClass(@RequestBody @Valid MaterialDTO materialDTO, @PathVariable @Valid UUID classId) {
//            return ResponseEntity.ok(classService.addMaterialToClass(materialDTO,  classId));
//    }

    @PostMapping("/uploadMultipleFiles/{classId}")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files, @PathVariable UUID classId) {
        return files.stream().map(file -> classService.uploadFile(file, classId)).collect(Collectors.toList());
    }


    @PostMapping("/assignment/{classId}")
    public ResponseEntity<Assignment> createAssigment(@RequestBody AssignmentDTO assignmentDTO, @PathVariable UUID classId) {
        return  ResponseEntity.ok(classService.createAssignment(assignmentDTO, classId));
    }

//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
//        ClassFile dbClassFile = dbFileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(dbClassFile.getId().toString())
//                .toUriString();
//
//        return new UploadFileResponse(dbClassFile.getFileName(), fileDownloadUri,
//                file.getContentType(), file.getSize());
//    }


}

