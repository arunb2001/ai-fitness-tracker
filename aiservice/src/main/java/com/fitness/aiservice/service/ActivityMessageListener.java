package com.fitness.aiservice.service;

/*
* This class is used to listen to kafka activity-events topic for new messages
*
* @RequiredArgsConstructor - used on a class, only injects final fields. If you want
* to inject non-final fields then use @AllArgsConstructor
*
* @KafkaListener - used on a method, new message from kafka will be deserialized
* into Activity object and passed as an argument to this method
* */

import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAIService activityAIService;
    private final RecommendationRepository repository;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-processor-group")
    public void processActivity(Activity activity) {
        log.info("received activity for processing: {}", activity.getUserId());

        /**
         * Call the ActivityAIService class to get the recommendations generated from
         * this activity
         * ActivityAIService.generateRecommendation()
         * GeminiService.getRecommendations()
         */
        Recommendation recommendation = activityAIService.generateRecommendation(activity);
        repository.save(recommendation);
    }
}
