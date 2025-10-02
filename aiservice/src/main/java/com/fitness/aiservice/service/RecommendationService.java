package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository repository;

    public List<Recommendation> getUserRecommendation(String userId) {
        return repository.findByUserId(userId);
    }

    /*
    * repository.findByUserId() returns Optional<Recommendation> which means it can
    * either return a Recommendation object or we can tell the returned object to do
    * something else (throw Exception, or return something else) if there is no
    * Recommendation object returned by the repository
    * */
    public Recommendation getActivityRecommendation(String activityId) {
        return repository.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("no recommendation found for this activity: " + activityId));
    }
}
