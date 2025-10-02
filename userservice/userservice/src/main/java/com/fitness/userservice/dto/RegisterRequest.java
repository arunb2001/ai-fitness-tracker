package com.fitness.userservice.dto;

/*
* Describes how the user HTTP request will be handled in the application
*
* We can add some validations to the request so that the user does not send
* invalid request or request in wrong format
*
* @NotBlank() - used on the data member, to validate that the field should
* not be blank
*
* @Email() - used on a data member, to validate that the field must be in
* an email format
*
* @Size() - used on a data member, to validate that the field should meet
* the given length constraint
* */

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "email required")
    @Email(message = "invalid email")
    private String email;

    private String keycloakId;

    @NotBlank(message = "password required")
    @Size(min = 6, message = "password must have atleast 6 characters")
    private String password;
    private String firstName;
    private String lastName;
}
