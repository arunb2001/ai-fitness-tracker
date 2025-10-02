package com.fitness.gateway.user;

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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    /*
     * Since we have marked WebClientConfig.userServiceWebClient() as a bean, spring boot
     * will store the reference of this method with itself, and inject the object returned
     * by this method into this field as the method name is same as the field name
     * */
    private final WebClient userServiceWebClient;

    public Mono<Boolean> validateUser(String userId) {
        log.info("Calling User Service for {}", userId);
        return userServiceWebClient.get()
                .uri("/api/users/{userId}/validate", userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND)
                        return Mono.error(new RuntimeException("User not found : " + userId));

                    else if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
                        return Mono.error(new RuntimeException("Invalid : " + userId));

                    return Mono.error(new RuntimeException("Unexpected error : " + userId));
                });
    }

    public Mono<UserResponse> registerUser(RegisterRequest registerRequest) {
        log.info("Calling User Registration for {}", registerRequest.getEmail());
        return userServiceWebClient.post()
                .uri("/api/users/register")
                .bodyValue(registerRequest)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
                        return Mono.error(new RuntimeException("Bad request : " + e.getMessage()));

                    return Mono.error(new RuntimeException("Unexpected error : " + e.getMessage()));
                });
    }
}
