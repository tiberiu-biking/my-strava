package com.tpo.fitme.strava.client.rest;


import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.activity.Activity;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRestClient {

    List<Activity> findAll(Athlete athlete);

    List<Activity> getAllAfter(Athlete athlete, LocalDateTime localDate);

    Activity getOne(Athlete athlete, String activityId);
}
