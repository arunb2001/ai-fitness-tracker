package com.example.activityservice;

/*
* This is an interface b/w the DB and the application. All the DB CRUD
* operations will be handled by this interface. The Service class just
* has to call the methods of this interface.
* This will extend MongoRepository<Activity, String>.
* <Activity, String> - The entry of an activity is mapped to an ID of String format.
* */

import com.example.activityservice.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findByUserId(String userId);
}
