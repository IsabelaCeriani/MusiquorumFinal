package com.lab1Spring.musiquorum.controllers;

import com.lab1Spring.musiquorum.dtos.AssignmentDTO;
import com.lab1Spring.musiquorum.dtos.CreateClassDTO;
import com.lab1Spring.musiquorum.models.*;
import com.lab1Spring.musiquorum.models.Class;
import com.lab1Spring.musiquorum.responses.UploadFileResponse;
import com.lab1Spring.musiquorum.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
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
    @GetMapping("/{courseId}")
    public ResponseEntity<List<Class>> getClassByCourse(@PathVariable UUID courseId) {
        return ResponseEntity.ok(classService.getClassByCourseId(courseId));
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

    @GetMapping("/assignments/{classId}")
    public ResponseEntity<List<Assignment>> getAssignments(@PathVariable UUID classId) {
        return ResponseEntity.ok(classService.getAssignments(classId));
    }

    @PostMapping("/assignment/upload/{assignmentId}")
    public ResponseEntity<Assignment> uploadAssignmentFile(@RequestParam("file") MultipartFile file, @PathVariable UUID assignmentId) {
        return ResponseEntity.ok(classService.uploadAssignmentFile(file, assignmentId));
    }


//    @PutMapping("/assignment/{classId}")
//    public ResponseEntity<Assignment> editAssigment(@RequestBody AssignmentDTO assignmentDTO, @PathVariable UUID classId) {
//        return  ResponseEntity.ok(classService.editAssigment(assignmentDTO, classId));
//    }


    @GetMapping("/downloadFiles/{classId}")
    public List<ResponseEntity<Resource>> downloadFile(@PathVariable UUID classId) {
        // Load file from database
        List<ClassFile> dbClassFiles = classService.getFile(classId);

        List<ResponseEntity<Resource>> resources = new ArrayList<>();

        dbClassFiles.forEach(dbClassFile -> resources.add(ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbClassFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbClassFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbClassFile.getData()))));

        return resources;
    }

    @GetMapping("/downloadAssignmentFile/{assignmentId}")
    public List<ResponseEntity<Resource>> downloadAssignmentFile(@PathVariable UUID assignmentId) {
        // Load file from database
        List<AssignmentFile> assignmentFiles = classService.getsAssignmentFile(assignmentId);

        List<ResponseEntity<Resource>> resources = new ArrayList<>();

        assignmentFiles.forEach(assignmentFile -> resources.add(ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(assignmentFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + assignmentFile.getFileName() + "\"")
                .body(new ByteArrayResource(assignmentFile.getData()))));

        return resources;

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

