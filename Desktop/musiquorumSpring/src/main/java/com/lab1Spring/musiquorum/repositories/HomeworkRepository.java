package com.lab1Spring.musiquorum.repositories;

import com.lab1Spring.musiquorum.models.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, UUID> {


    Homework findByUseridAndAssignmentId(UUID userid, UUID assignmentId);

    List<Homework> findByUserid(UUID userid);





}
