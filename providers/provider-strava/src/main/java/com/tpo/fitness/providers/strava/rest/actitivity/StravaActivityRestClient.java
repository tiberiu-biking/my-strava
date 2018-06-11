package com.tpo.fitness.providers.strava.rest.actitivity;

import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.domain.activity.Activity;
import com.tpo.fitness.providers.api.service.ActivityRestClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.tpo.fitness.providers.strava.rest.actitivity.StravaURIBuilder.*;

@Slf4j
@Service
public class StravaActivityRestClient implements ActivityRestClient {

    private final RestTemplate restTemplate;

    public StravaActivityRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Retryable
    public List<Activity> findAll(Athlete athlete) {
        List<StravaActivity> activities = new ArrayList<>();
        boolean isPageLeft = true;
        int page = 1;
        while (isPageLeft) {
            String uriString = buildAthleteActivitiesByPageUrl(athlete.getAuthToken(), page);
            log.info("Loading activities page {} using url  {}", uriString);
            StravaActivity[] summaryActivityList = restTemplate.getForObject(uriString, StravaActivity[].class);
            activities.addAll(Arrays.asList(summaryActivityList));
            isPageLeft = !ArrayUtils.isEmpty(summaryActivityList);
            page++;
        }
        log.info("Found {} activities", activities.size());

        return activities.stream().map(StravaActivityTranslator::translate).collect(Collectors.toList());
    }

    @Override
    @Retryable
    public List<Activity> getAllAfter(Athlete athlete, long after) {
        List<StravaActivity> activities = new ArrayList<>();

        boolean isPageLeft = true;
        int page = 1;

        while (isPageLeft) {
            String uriString = buildAthleteActivitiesAfterUrl(athlete.getAuthToken(), after, page);
            log.info("Finding activities after {} using uri {}", after, uriString);
            StravaActivity[] summaryActivityList = restTemplate.getForObject(uriString, StravaActivity[].class);
            activities.addAll(Arrays.asList(summaryActivityList));
            isPageLeft = !ArrayUtils.isEmpty(summaryActivityList);
            page++;
        }

        log.info("Found {} activities after {}", activities.size(), new Date(after * 1000));
        return activities.stream().map(StravaActivityTranslator::translate).collect(Collectors.toList());
    }


    @Override
    @Retryable(label = "getOne")
    public Activity getOne(Athlete athlete, String activityId) {
        String uriString = buildActivityDetailsURL(athlete.getAuthToken(), activityId);
        log.info("Get activity details using url {}", uriString);
        StravaActivity stravaActivity = restTemplate.getForObject(uriString, StravaActivity.class);
        return StravaActivityTranslator.translate(stravaActivity);
    }

}
