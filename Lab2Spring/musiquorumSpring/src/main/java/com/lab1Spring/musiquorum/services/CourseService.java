package com.lab1Spring.musiquorum.services;

import com.lab1Spring.musiquorum.dtos.CourseDTO;
import com.lab1Spring.musiquorum.dtos.CreateCourseDTO;
import com.lab1Spring.musiquorum.exceptions.BadRequestException;
import com.lab1Spring.musiquorum.models.Course;
import com.lab1Spring.musiquorum.repositories.CourseRepository;
import com.lab1Spring.musiquorum.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lab1Spring.musiquorum.models.*;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import java.util.UUID;

@Service
@Validated
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public List<Course> getAllCourses(){
        return new ArrayList<Course>((Collection<? extends Course>) courseRepository.findAll());
    }



    public CourseDTO addCourse(CreateCourseDTO courseDTO) {
        User prof = userService.getCurrentUser();
        Course course = new Course(courseDTO.getName(), courseDTO.getDescription(), prof.getId(), courseDTO.getTags(), courseDTO.getImg());
        courseRepository.save(course);
        CourseDTO courseDTO1 = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO1);
        return courseDTO1;
    }

    public  CourseDTO editCourse(CreateCourseDTO courseDTO){
        Course course = courseRepository.findById(courseDTO.getEventId()).orElseThrow(() -> new BadRequestException("Could not find event"));
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setTags(courseDTO.getTags());
        course.setImageUrl(courseDTO.getImg());
        courseRepository.save(course);
        CourseDTO courseDTO1 = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO1);
        return courseDTO1;
    }

    public CourseDTO deleteCourse(UUID courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new BadRequestException("Could not find event"));
        courseRepository.delete(course);
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        return courseDTO;
    }

    public List<Course> getTeacherCourses() {
        User user = userService.getCurrentUser();
        return courseRepository.findByProfessorId(user.getId());
    }
}
