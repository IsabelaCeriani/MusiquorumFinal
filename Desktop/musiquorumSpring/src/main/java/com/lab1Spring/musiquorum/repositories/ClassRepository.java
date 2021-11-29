package com.lab1Spring.musiquorum.repositories;

import com.lab1Spring.musiquorum.models.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<Class, UUID> {
}
