package com.fitness.userservice;

import com.fitness.userservice.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
* Here each new entry to the users table is associated with a UUID in String
* format so we map User class to a String class. Hence,
* JpaRepository<User, String>
* */

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByEmail(String email);

    Boolean existsByKeycloakId(String userId);

    User findByEmail(@NotBlank(message = "email required") @Email(message = "invalid email") String email);
}
