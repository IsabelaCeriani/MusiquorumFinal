package com.lab1Spring.musiquorum.services;

import com.lab1Spring.musiquorum.dtos.AssignmentDTO;
import com.lab1Spring.musiquorum.dtos.CreateClassDTO;
import com.lab1Spring.musiquorum.exceptions.BadRequestException;
import com.lab1Spring.musiquorum.exceptions.FileStorageException;
import com.lab1Spring.musiquorum.models.*;
import com.lab1Spring.musiquorum.models.Class;
import com.lab1Spring.musiquorum.models.AssignmentFile;
import com.lab1Spring.musiquorum.repositories.*;
import com.lab1Spring.musiquorum.responses.UploadFileResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class ClassService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private AssignmentFileRepository assignmentFileRespoitory;

    @Autowired
    private AssignmentRepository assignmentRepository;


    public CreateClassDTO addClass(CreateClassDTO classDTO, UUID courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new BadRequestException("Could not find course"));
        Class classToSave = new Class(classDTO.getClassName(), classDTO.getDuration(), course);
        classRepository.save(classToSave);
        return classDTO;

    }

    public Class getClassById(UUID id) {
        return classRepository.findById(id).orElseThrow(() -> new BadRequestException("Could not find class"));
    }

    public Class updateClass(CreateClassDTO classDTO, UUID id) {
        Class classToBeEdited = classRepository.findById(id).orElseThrow(() -> new BadRequestException("Could not find class"));
        BeanUtils.copyProperties(classDTO, classToBeEdited);
        return classRepository.save(classToBeEdited);
    }

    public ClassFile saveFile(MultipartFile multipartFile, UUID classId) {
        Class class_ = classRepository.findById(classId).orElseThrow(() -> new BadRequestException("Could not find class"));
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            ClassFile dbClassFile = new ClassFile(fileName, multipartFile.getContentType(), new Date(), multipartFile.getBytes(), class_);

            return fileRepository.save(dbClassFile);
        } catch (IOException | FileStorageException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }

    }

    public void saveAssignmentFile(MultipartFile multipartFile, Assignment assignment) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            AssignmentFile assignmentFile = new AssignmentFile(fileName, multipartFile.getContentType(), new Date(), assignment, multipartFile.getBytes());

            assignmentFileRespoitory.save(assignmentFile);
        } catch (IOException | FileStorageException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }

    }

    public UploadFileResponse uploadFile(MultipartFile multipartFile, UUID classId) {
        ClassFile classFile = this.saveFile(multipartFile, classId);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(classFile.getId().toString())
                .toUriString();

        return new UploadFileResponse(classFile.getFileName(), fileDownloadUri,
                multipartFile.getContentType(), multipartFile.getSize());
    }


    public Assignment createAssignment(AssignmentDTO assignmentDTO, UUID classId) {
        Class class_ = classRepository.findById(classId).orElseThrow(() -> new BadRequestException("Could not find class"));
        Assignment assignment = new Assignment(assignmentDTO.getName(),class_, assignmentDTO.getInstructions());
        assignmentDTO.getFiles().forEach(file -> saveAssignmentFile(file, assignment));
        return assignmentRepository.save(assignment);

    }
}
