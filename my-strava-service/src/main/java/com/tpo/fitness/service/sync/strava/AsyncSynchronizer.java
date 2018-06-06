package com.tpo.fitness.service.sync.strava;

import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.domain.activity.Activity;
import com.tpo.fitness.providers.api.service.ActivityRestClient;
import com.tpo.fitness.service.sync.Synchronizer;
import com.tpo.strava.persistence.service.repository.repository.ActivityDatabaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tiberiu
 * @since 04.10.17
 */
@Slf4j
@Service
public class AsyncSynchronizer implements Synchronizer {

    private final ActivityRestClient         stravaActivityRestClient;
    private final ActivityDatabaseRepository activityDatabaseRepository;

    @Autowired
    public AsyncSynchronizer(ActivityRestClient stravaActivityRestClient,
                             ActivityDatabaseRepository activityDatabaseRepository) {
        this.stravaActivityRestClient = stravaActivityRestClient;
        this.activityDatabaseRepository = activityDatabaseRepository;
    }

    @Async
    public void sync(Athlete athlete) {
        log.info("Starting sync athlete {} activities...", athlete.getId());
        Long lastStartDate = activityDatabaseRepository.getLastStartDateByAthlete(athlete);

        List<Activity> activities = stravaActivityRestClient.findAllAfter(athlete, lastStartDate);

        log.info("Found {} new activities", activities.size());
        activities.stream()
                .map(activity -> stravaActivityRestClient.getOne(athlete, activity.getId()))
                .forEach(this::persistActivity);

        log.info("Sync done!");
    }

    private void persistActivity(Activity activity) {
        activityDatabaseRepository.save(activity);
        log.debug("Persisted activity with id {}", activity.getId());
    }
}
