package com.example.activityservice.dto;

/*
* The object of this class will contain all the info the user provides
* in its HTTP request. This makes it easier to get all the required info
* from the request.
* */

import com.example.activityservice.model.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityRequest {
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
}
