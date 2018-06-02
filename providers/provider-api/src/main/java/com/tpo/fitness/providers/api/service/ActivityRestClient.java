package com.tpo.fitness.providers.api.service;


import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.domain.activity.Activity;

import java.util.List;

/**
 * A REST client for retrieving activities from an activity provider.
 *
 * @author Tiberiu
 * @since 08.10.17
 */
public interface ActivityRestClient {

    List<Activity> getAllActivitiesByAthlete(Athlete athlete);

    List<Activity> findActivitiesAfterByAthlete(Athlete athlete, long after);

    Activity findOneByAthleteId(Athlete athlete, String activityId);
}
