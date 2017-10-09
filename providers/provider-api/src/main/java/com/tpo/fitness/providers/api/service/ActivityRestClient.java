package com.tpo.fitness.providers.api.service;


import com.tpo.fitness.domain.activity.Activity;

import java.util.List;

/**
 * A REST client for retrieving activities from an activity provider.
 *
 * @author Tiberiu
 * @since 08.10.17
 */
public interface ActivityRestClient {

    List<Activity> getAllActivities();

    List<Activity> findActivities(long after);

    Activity getActivity(String activityId);
}
