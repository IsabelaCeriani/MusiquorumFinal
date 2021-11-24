package com.lab1Spring.musiquorum.repositories;

import com.lab1Spring.musiquorum.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {



}
