package com.tpo.fitme.strava.client.rest.actitivity;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.activity.Activity;
import com.tpo.fitme.strava.client.rest.ActivityRestClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.tpo.fitme.strava.client.rest.actitivity.StravaURIBuilder.*;

@Slf4j
@Service
public class StravaActivityRestClient implements ActivityRestClient {

    private static final int MAX_RETRIES = 10;
    private final RestTemplate restTemplate;
    private final StravaActivityMapper stravaActivityMapper;

    @Autowired
    public StravaActivityRestClient(RestTemplate restTemplate, StravaActivityMapper stravaActivityMapper) {
        this.restTemplate = restTemplate;
        this.stravaActivityMapper = stravaActivityMapper;
    }

    @Override
    @Retryable(maxAttempts = MAX_RETRIES)
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

        return activities.stream().map(stravaActivityMapper::map).collect(Collectors.toList());
    }

    @Override
    @Retryable(maxAttempts = MAX_RETRIES)
    public List<Activity> getAllAfter(Athlete athlete, LocalDateTime after) {
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

        log.info("Found {} activities after {}", activities.size(), after);
        return activities.stream().map(stravaActivityMapper::map).collect(Collectors.toList());
    }


    @Override
    @Retryable(maxAttempts = MAX_RETRIES)
    public Activity getOne(Athlete athlete, String activityId) {
        String uriString = buildActivityDetailsURL(athlete.getAuthToken(), activityId);
        log.info("Get activity details using url {}", uriString);
        StravaActivity stravaActivity = restTemplate.getForObject(uriString, StravaActivity.class);
        return stravaActivityMapper.map(stravaActivity);
    }

}
