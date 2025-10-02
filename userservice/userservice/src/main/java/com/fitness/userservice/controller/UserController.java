package com.fitness.userservice.controller;

/*
* This class contains all the API endpoints related to user mgmt. for example,
* if a user wants to register on our platform, the /register URL endpoint
* will be handled here.
*
* @RestController -
*
* @RequestMapping -
*
* @AllArgsConstructor - used on the class. Auto-initializes all the data
* members
*
* @RequestBody -
*
* @Valid - validate all the fields in the object before running the method
* */

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    /*
    * API endpoint to handle display user profile request
    * */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    /*
    * API endpoint to handle new user registration
    * */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok(userService.existByUserId(userId));
    }
}
