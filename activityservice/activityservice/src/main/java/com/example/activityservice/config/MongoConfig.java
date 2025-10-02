package com.example.activityservice.config;

/*
* Config class for communicating with our mongodb.
*
* @EnableMongoAuditing - used on this config class, enables use of the
* mongodb specfic annotations like @CreatedDate, etc otherwise null values
* will be stored in DB
* */

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class MongoConfig {
}
