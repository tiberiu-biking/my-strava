package com.tpo.fitness.providers.api.service;


import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.activity.Activity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A REST client for retrieving activities from an activity provider.
 *
 * @author Tiberiu
 * @since 08.10.17
 */
public interface ActivityRestClient {

    List<Activity> findAll(Athlete athlete);

    List<Activity> getAllAfter(Athlete athlete, LocalDateTime localDate);

    Activity getOne(Athlete athlete, String activityId);
}
