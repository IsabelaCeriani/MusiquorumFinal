package com.lab1Spring.musiquorum.repositories;

import com.lab1Spring.musiquorum.models.Assignment;
import com.lab1Spring.musiquorum.models.AssignmentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssignmentFileRepository extends JpaRepository<AssignmentFile, UUID> {
}

