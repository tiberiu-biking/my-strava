package com.tpo.fitness.providers.strava.rest.actitivity;

import com.tpo.fitness.domain.activity.Activity;
import com.tpo.fitness.providers.api.service.ActivityRestClient;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.tpo.fitness.providers.strava.rest.actitivity.StravaURIBuilder.*;

@Service
public class StravaActivityRestClient implements ActivityRestClient {

    private static final Logger logger = LoggerFactory.getLogger(StravaActivityRestClient.class);

    private final RestTemplate restTemplate;
    private final String accessToken;

    public StravaActivityRestClient(String accessToken) {
        this.accessToken = accessToken;
        restTemplate = new RestTemplate();
    }

    @Override
    public List<Activity> getAllActivities() {
        List<StravaActivity> activities = new ArrayList<>();
        boolean isPageLeft = true;
        int page = 1;
        while (isPageLeft) {
            String uriString = buildAthleteActivitiesByPageUrl(accessToken, page);
            logger.info("Loading activities page {} using url  {}", uriString);
            StravaActivity[] summaryActivityList = restTemplate.getForObject(uriString, StravaActivity[].class);
            activities.addAll(Arrays.asList(summaryActivityList));
            isPageLeft = !ArrayUtils.isEmpty(summaryActivityList);
            page++;
        }
        logger.info("Found {} activities", activities.size());


        return getDetailedActivities(activities);
    }

    @Override
    public List<Activity> findActivities(long after) {
        List<StravaActivity> activities = new ArrayList<>();
        boolean isPageLeft = true;
        int page = 1;
        while (isPageLeft) {
            String uriString = buildAthleteActivitiesAfterUrl(accessToken, after, page);
            logger.info("Finding activities after {} using uri {}", after, uriString);
            StravaActivity[] summaryActivityList = restTemplate.getForObject(uriString, StravaActivity[].class);
            activities.addAll(Arrays.asList(summaryActivityList));
            isPageLeft = !ArrayUtils.isEmpty(summaryActivityList);
            page++;
        }

        logger.info("Found {} activities after {}", activities.size(), new Date(after * 1000));
        return getDetailedActivities(activities);
    }


    @Override
    public Activity getActivity(String activityId) {
        String uriString = buildActivityDetailsURL(accessToken, activityId);
        logger.info("Get activity details using url {}", uriString);

        StravaActivity stravaActivity = restTemplate.getForObject(uriString, StravaActivity.class);
        return StravaActivityTranslator.translate(stravaActivity);
    }


    private List<Activity> getDetailedActivities(List<StravaActivity> activities) {
        return activities.stream()
                .map(stravaActivity -> getActivity(stravaActivity.getId()))
                .collect(Collectors.toList());
    }


}
