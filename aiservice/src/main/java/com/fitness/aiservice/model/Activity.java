package com.fitness.aiservice.model;

/*
* This is a model class that defines how do we store the activity-related
* data in mongodb
*
* @Document - used on a class, describes the schema to store data in mongodb
* similar to @Entity for JPA (SQL). You give the name of the collection
* inside the annotation
*
* @Builder - used on a class, annotation to allow created of the object of this
* class using builder design pattern
*
* @Field - used on a data member, renames the field to a specific name in
* mongodb
*
* @CreatedDate - used on a data member, adds the date of creation of the entry
* to this field in mongodb
*
* @LastModifiedDate - used on a data member, adds the date of updation of
* this entry to this field in mongodb
* */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

/*
* This class from Activity Service is used to deserialize the message
* from kafka
* */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private String id;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;

    @Field("metrics")
    private Map<String, Object> additionalMetrics;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
