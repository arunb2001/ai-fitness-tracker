package com.fitness.userservice.models;

/*
* User model class. This class will be represented as a table in DB and its
* data members will be columns in the table. Model classes will only have
* setters and getters.
*
* @Entity - describes that the class is a model class or an entity class.
* The table name will be the same as class name. But User table name would
* already exist for some other internal purpose.
*
* @Table(name = "users") - overrides the table name for this model class to be
* the given name instead of class name.
*
* @Data - A lombok annotation used to generate getters and setters
*
* The model class must have an identifier field. More specifically, @Entity
* classes must have @Id property.
*
* @Id
* @GeneratedValue(strategy = GenerationType.UUID)
*
* These two annotations will ensure that User class will have an identifier in
* table, which will be auto-generated (UUID) for each entry in the table
*
* @Column - used on a data member, to add data validations on that member
* for example, @Column(unique = true) on email variable will ensure that the
* email for each entry in the table is unique
*
* @Enumerated - used on enum objects to specify which format should be used
* to store the enum values in the table
*
* @CreationTimestamp - used on a data member, assigns the current date & time
* to that member of the new entry, when adding it to the table
*
* @UpdateTimestamp - used on a data member, assigns the current date & time
* when an existing entry is being updated
* */

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;  // The UUID in a String format

    // user ID stored in keycloak oauth server
    private String keycloakId;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
