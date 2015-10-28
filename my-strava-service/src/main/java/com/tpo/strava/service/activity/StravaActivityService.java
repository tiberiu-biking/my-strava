package com.tpo.strava.service.activity;

import com.tpo.strava.service.client.StravaRestClient;
import com.tpo.strava.service.domain.activity.Activity;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiberiu on 25/10/15.
 */
@SpringComponent
@UIScope
public class StravaActivityService implements ActivityService {

    private static final Logger logger = LoggerFactory.getLogger(StravaActivityService.class);

    @Value("${strava.oauth.token}")
    private String authToken;

    @Override
    public List<Activity> getActivities() {
        logger.info("Getting activities using authorization code: " + authToken);
        StravaRestClient stravaRestClient = new StravaRestClient(authToken);
        List<Activity> summaryActivityList = stravaRestClient.getActivities();

        List<Activity> activities = new ArrayList<>();
        for (Activity summaryActivity : summaryActivityList) {
            Activity activity = stravaRestClient.getActivity(summaryActivity.getId());
            activities.add(activity);
        }

        return activities;
    }
}
