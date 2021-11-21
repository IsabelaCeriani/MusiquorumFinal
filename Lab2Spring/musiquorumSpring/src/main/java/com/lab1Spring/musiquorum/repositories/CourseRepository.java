package com.lab1Spring.musiquorum.repositories;

import com.lab1Spring.musiquorum.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.lab1Spring.musiquorum.models.Course;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends CrudRepository<Course, UUID> {



    List<Course> findByProfessorId(UUID professorId);


}
