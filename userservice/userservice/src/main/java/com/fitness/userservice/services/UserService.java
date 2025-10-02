package com.fitness.userservice.services;

/*
* Handles the internal business logic related to user mgmt
* UserService will be called by UserController to manage the CRUD operations
* on users.
*
* @Service -
*
* How do we register the user in our DB?
* We create a Repository interface (extends JpaRepository<User>),
* which will communicate with the DB
* */

import com.fitness.userservice.UserRepository;
import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserResponse register(RegisterRequest request) {

        /*
        * We want to add some validation before saving the new entry to the DB
        * Currently UserRepository only has exists(), existsById()
        * We can create a new method existsByEmail() since email is unique
        * for each entry
        *
        * The best part about JPA is that if you create a new method in a
        * specific format, JPA will understand the purpose of the new method
        * and generates the query automatically. We don't have to implement
        * the method.
        * */
        if (repository.existsByEmail(request.getEmail())) {
            User existingUser = repository.findByEmail(request.getEmail());
            UserResponse userResponse = new UserResponse();
            userResponse.setId(existingUser.getId());
            userResponse.setPassword(existingUser.getPassword());
            userResponse.setEmail(existingUser.getEmail());
            userResponse.setFirstName(existingUser.getFirstName());
            userResponse.setLastName(existingUser.getLastName());
            userResponse.setCreatedAt(existingUser.getCreatedAt());
            userResponse.setUpdatedAt(existingUser.getUpdatedAt());
            userResponse.setKeycloakId(existingUser.getKeycloakId());
        }

        // creating the User object from the user's request
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setKeycloakId(request.getKeycloakId());    // when user is authenticated using keycloak

        /*
        * You save the user object as a new entry in the DB by calling
        * repository.save(user)
        *
        * The return type is the new entry as an object of User class
        * that has been saved in the DB
        *
        * As a response to the user request, we send all the fields of the
        * new entry created in the DB
        * */
        User savedUser = repository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());
        userResponse.setKeycloakId(savedUser.getKeycloakId());

        return userResponse;
    }


    /*
    * method to fetch the profile of a particular user and return it
    * to the user. we find the entry using its UUID which is in the String
    * format
    * */
    public UserResponse getUserProfile(String userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        // return all the user details as a response to the request
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setPassword(user.getPassword());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;
    }

    /*
    * method to check whether the given user ID exists in the user service
    * DB or not
    * */
    public Boolean existByUserId(String userId) {
//        return repository.existsById(userId);

        // instead of the primary key id (in User object) we want to find
        // the user by its keycloak user ID
        return repository.existsByKeycloakId(userId);
    }
}
