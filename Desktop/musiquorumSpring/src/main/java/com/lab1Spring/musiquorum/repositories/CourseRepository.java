package com.lab1Spring.musiquorum.repositories;

import com.lab1Spring.musiquorum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.lab1Spring.musiquorum.models.Course;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findByName(String name);

    List<Course> findByNameContains(String name);

    List<Course> findByTagsName(String name);




    //method that returns all courses that contain all the tags in the list
    List<Course> findByTagsNameIn(List<String> tags);











    List<Course> findByProfessorId(UUID professorId);


}
