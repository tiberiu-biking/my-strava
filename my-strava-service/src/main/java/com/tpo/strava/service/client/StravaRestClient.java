package com.tpo.strava.service.client;

import com.tpo.strava.service.domain.Athlete;
import com.tpo.strava.service.domain.activity.Activity;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tiberiu on 28/10/15.
 */
public class StravaRestClient {
    private static final String ATHLETE_ACTIVITIES_URL = "https://www.strava.com/api/v3/athlete/activities";
    private static final String ACTIVITIES_URL = "https://www.strava.com/api/v3/activities";
    private static final String ATHLETE_URL = "https://www.strava.com/api/v3/athlete";

    private static final String ACCESS_TOKEN = "access_token";
    private static final String PAGE = "page";
    private static final String AFTER = "after";

    private static final Logger logger = LoggerFactory.getLogger(StravaRestClient.class);

    private RestTemplate restTemplate;
    private String accessToken;

    public StravaRestClient(String accessToken) {
        this.accessToken = accessToken;
        restTemplate = new RestTemplate();
    }

    public List<Activity> getActivities() {
        List<Activity> activities = new ArrayList<>();
        boolean isPagesLeft = true;
        int page = 1;
        while (isPagesLeft) {
            String uriString = UriComponentsBuilder
                    .fromUriString(ATHLETE_ACTIVITIES_URL)
                    .queryParam(ACCESS_TOKEN, accessToken)
                    .queryParam(PAGE, page)
                    .toUriString();
            logger.info("Activities summary uri  {}", uriString);
            Activity[] summaryActivityList = restTemplate.getForObject(uriString, Activity[].class);
            isPagesLeft = !ArrayUtils.isEmpty(summaryActivityList);
            activities.addAll(Arrays.asList(summaryActivityList));
            page++;
        }
        return activities;
    }

    public List<Activity> findActivities(long after) {
        List<Activity> activities = new ArrayList<>();
        String uriString = UriComponentsBuilder
                .fromUriString(ATHLETE_ACTIVITIES_URL)
                .queryParam(ACCESS_TOKEN, accessToken)
                .queryParam(AFTER, after)
                .toUriString();
        logger.info("Finding activities after {} using uri {}", after, uriString);
        Activity[] summaryActivityList = restTemplate.getForObject(uriString, Activity[].class);
        activities.addAll(Arrays.asList(summaryActivityList));
        return activities;
    }

    public Activity getActivity(String activityId) {
        String uriString = UriComponentsBuilder
                .fromUriString(ACTIVITIES_URL)
                .queryParam(ACCESS_TOKEN, accessToken)
                .pathSegment(activityId)
                .toUriString();
        logger.info("ActivityEntity details uri {}", uriString);
        return restTemplate.getForObject(uriString, Activity.class);
    }

    public Athlete getAthlete() {
        String uriString = UriComponentsBuilder
                .fromUriString(ATHLETE_URL)
                .queryParam(ACCESS_TOKEN, accessToken)
                .toUriString();
        logger.debug("ActivityEntity details uri {}", uriString);
        return restTemplate.getForObject(uriString, Athlete.class);
    }

}
