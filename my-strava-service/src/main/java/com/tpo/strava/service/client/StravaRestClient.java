package com.tpo.strava.service.client;

import com.tpo.strava.service.domain.activity.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tiberiu on 28/10/15.
 */
public class StravaRestClient {
    private static final String ATHLETE_ACTIVITIES_URL = "https://www.strava.com/api/v3/athlete/activities";
    private static final String ACTIVITIES_URL = "https://www.strava.com/api/v3/activities";
    private static final String ACCESS_TOKEN = "access_token";

    private static final Logger logger = LoggerFactory.getLogger(StravaRestClient.class);

    private RestTemplate restTemplate;
    private String accessToken;

    public StravaRestClient(String accessToken) {
        this.accessToken = accessToken;
        restTemplate = new RestTemplate();
    }

    public List<Activity> getActivities() {
        String uriString = UriComponentsBuilder
                .fromUriString(ATHLETE_ACTIVITIES_URL)
                .queryParam(ACCESS_TOKEN, accessToken)
                .toUriString();
        logger.debug("Activities summary uri: " + uriString);
        Activity[] summaryActivityList = restTemplate.getForObject(uriString, Activity[].class);
        return Arrays.asList(summaryActivityList);
    }

    public Activity getActivity(String activityId) {
        String uriString = UriComponentsBuilder
                .fromUriString(ACTIVITIES_URL)
                .queryParam(ACCESS_TOKEN, accessToken)
                .pathSegment(activityId)
                .toUriString();
        logger.debug("Activity details uri: " + uriString);
        return restTemplate.getForObject(uriString, Activity.class);
    }

}
