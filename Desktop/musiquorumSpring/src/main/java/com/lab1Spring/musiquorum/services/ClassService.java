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

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private MailService mailService;

    @Autowired
    private HomeworkRepository homeworkRepository;


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
        class_.getCourse().getEnrolledUsers().forEach(s-> {
            try {
                homeworkRepository.save(new Homework(s.getId(), assignment, HomeworkStatus.NOT_SUBMITTED));
                mailService.sendAssignmentEmail(s.getEmail(), assignment);
            } catch (MessagingException | IOException e) {
                e.printStackTrace();
            }
        });
        return assignmentRepository.save(assignment);

    }

    public List<ClassFile> getFile(UUID classId) {
        return fileRepository.findByClasss_Id(classId);
    }

    public List<AssignmentFile> getsAssignmentFile(UUID assignmentId) {
            return assignmentFileRespoitory.findByAssignment_Id(assignmentId);
    }

    public List<Class> getClassByCourseId(UUID courseId) {
        return classRepository.findByCourse_Id(courseId);
    }

    public Assignment uploadAssignmentFile(MultipartFile file, UUID assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new BadRequestException("Could not find assignment"));
        saveAssignmentFile(file, assignment);
        return assignment;
    }

//    public Assignment editAssigment(AssignmentDTO assignmentDTO, UUID classId) {
//    }
}
