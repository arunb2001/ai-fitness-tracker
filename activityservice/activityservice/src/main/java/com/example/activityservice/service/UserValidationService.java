package com.example.activityservice.service;

/*
* a separate service which will just handle communication with the user service.
* It will take a WebClient object, call the user service API (as URL endpoint
* /api/users/{userId}/validate) to
* validate the given user ID.
*
* @Slf4j - used on a class, logging annotation, can be called using log.info() or
* log.debug(). Here 'log' will automatically be resolved by Slf4j.
* */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {

    /*
     * Since we have marked WebClientConfig.userServiceWebClient() as a bean, spring boot
     * will store the reference of this method with itself, and inject the object returned
     * by this method into this field as the method name is same as the field name
     * */
    private final WebClient userServiceWebClient;

    public boolean validateUser(String userId) {
        log.info("calling user service for {}", userId);
        try {
            return userServiceWebClient.get()
                    .uri("/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.info("oh no! exception");
            e.printStackTrace();
        }
        return false;
    }
}
