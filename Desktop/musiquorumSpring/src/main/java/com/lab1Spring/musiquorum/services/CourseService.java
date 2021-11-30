package com.lab1Spring.musiquorum.services;

import com.lab1Spring.musiquorum.dtos.CourseDTO;
import com.lab1Spring.musiquorum.dtos.CreateCourseDTO;
import com.lab1Spring.musiquorum.dtos.EditCourseDTO;
import com.lab1Spring.musiquorum.exceptions.BadRequestException;
import com.lab1Spring.musiquorum.models.Course;
import com.lab1Spring.musiquorum.repositories.CourseRepository;
import com.lab1Spring.musiquorum.repositories.TagRepository;
import com.lab1Spring.musiquorum.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.lab1Spring.musiquorum.models.*;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Validated
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserService userService;

    public List<Course> getAllCourses(){
        return new ArrayList<Course>((Collection<? extends Course>) courseRepository.findAll());
    }

    public Course getCourseById(UUID courseId){
        return courseRepository.findById(courseId).orElseThrow(() -> new BadRequestException("Could not find course"));
    }


    public List<Tag> saveOrGetTag(List<String> toBeTag){
        List<Tag> tags = new ArrayList<Tag>();
        for (String tag : toBeTag) {
            if (tagRepository.findFirstByName(tag.toLowerCase(Locale.ROOT)).isPresent()) tags.add(tagRepository.findFirstByName(tag.toLowerCase(Locale.ROOT)).get());
            else tags.add(tagRepository.save((new Tag(tag.toLowerCase(Locale.ROOT)))));
        }

        return tags;
    }

    public CourseDTO addCourse(CreateCourseDTO courseDTO, UUID professorId) {
        User prof = userRepository.findById(professorId).orElseThrow(() -> new BadRequestException("Could not find user"));
//        isValidData(courseDTO);
        Course course = new Course(courseDTO.getName(), courseDTO.getDescription(), prof.getId(), courseDTO.getImg());
        course.setTags(saveOrGetTag(courseDTO.getTags()));
        courseRepository.save(course);
        CourseDTO courseDTO1 = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO1);
        return courseDTO1;
    }



    public  CourseDTO editCourse(EditCourseDTO courseDTO, UUID courseID){
        Course course = courseRepository.findById(courseID).orElseThrow(() -> new BadRequestException("Could not find course"));

//          isValidEditData(courseDTO, course);
          course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());

        List<Tag> tagsToAdd = new ArrayList<Tag>();
        courseDTO.getTagsToAdd().forEach(tag -> tagsToAdd.add(new Tag(tag)));

        List<Tag> tagsToRemove = new ArrayList<Tag>();
        courseDTO.getTagsToRemove().forEach(tag -> tagsToRemove.add(new Tag(tag)));
        tagRepository.saveAll(tagsToAdd);
        course.getTags().addAll(tagsToAdd);
        course.getTags().removeAll(tagsToRemove);
        course.setImageUrl(courseDTO.getImageUrl());
        courseRepository.save(course);
        CourseDTO courseDTO1 = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO1);
        return courseDTO1;
    }

    public CourseDTO deleteCourse(UUID courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new BadRequestException("Could not find course"));
        courseRepository.delete(course);
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        return courseDTO;
    }

    public List<Course> getTeacherCourses(UUID professorId) {
        User user = userRepository.findById(professorId).orElseThrow(() -> new BadRequestException("Could not find user"));
        return courseRepository.findByProfessorId(user.getId());
    }

    public List<Course> getCoursesByTitle(String courseName) {
        return courseRepository.findByNameContains(courseName);
    }

    public List<Course> getCourseByTags(List<String> tags){
        return courseRepository.findByTagsNameIn(tags);
    }

    public Course enrollToCourse(UUID courseId, UUID userId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new BadRequestException("Could not find course"));
        User user = userRepository.findById(userId).orElseThrow(() -> new BadRequestException("Could not find user"));
        if(course.getEnrolledUsers().contains(user)) throw new BadRequestException("User is already enrolled to this course");
        course.getEnrolledUsers().add(user);
        courseRepository.save(course);
        return course;
    }


    public List<Course> getEnrolledInCourses(UUID userID) {
        return getAllCourses().stream()
                .filter(course -> course.getEnrolledUsers()
                .contains(userRepository.findById(userID).orElseThrow(() -> new BadRequestException("Could not find user"))))
                .collect(Collectors.toList());
    }
}
