package com.fitness.userservice.dto;

/*
* A Data Transfer Object is a design pattern that allows for the exchange of
* data between the business logic layer and the presentation or persistence layer.
* The primary goal of DTOs is to reduce the number of method calls between these
* layers by aggregating data into a single object.
*
* This way, the service layer communicates with the controller using DTOs,
* ensuring a clean separation between the data transfer and business logic.
*
* The data members will be almost similar to those in User model class
*
* @Data - used on the class, allows us to auto-create getters and setters
* */

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private String id;
    private String keycloakId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
