package com.tpo.strava.service.activity;

import com.tpo.strava.service.domain.ActivitiesSummary;
import com.tpo.strava.service.domain.activity.Activity;

import java.util.List;

/**
 * Created by Tiberiu on 25/10/15.
 */
public interface RemoteActivityService {

    List<Activity> findActivities(long after);

    List<ActivitiesSummary> getSummary();
}
