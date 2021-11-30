package com.lab1Spring.musiquorum.repositories;
import com.lab1Spring.musiquorum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

//    Optional<User> findByEmail(String email);
    
    

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);


}