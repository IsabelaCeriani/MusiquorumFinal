package com.lab1Spring.musiquorum.repositories;

import com.lab1Spring.musiquorum.models.HomeworkFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HomeworkFileRepo extends JpaRepository<HomeworkFile, UUID> {
}
