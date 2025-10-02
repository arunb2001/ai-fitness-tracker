package com.example.activityservice.config;

/*
* Config for webclient which handles inter-service communication between
* activity service and user service
*
* @Configuration - identifies a class as a config class
*
* Here we implement a method that returns a builder object of webclient.
* We add @Bean to the method so that it can be available anywhere in our
* activityservice project and we can add it as a field to a class with
* @Autowired.
*
* @LoadBalanced - very important for inter-service communication. using
* this we can call our user service APIs using the name of the service
* instead of the complete URL which can keep changing.
* */

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    /*
    * If we call the below API then using the returned object we can call APIs of
    * user service, from anywhere in the activity service.
    * */

    @Bean
    public WebClient userServiceWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl("http://USER-SERVICE").build();  // Eureka will help identify what is USERSERVICE
    }
}
