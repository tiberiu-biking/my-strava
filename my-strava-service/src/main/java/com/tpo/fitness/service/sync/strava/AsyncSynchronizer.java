package com.tpo.fitness.service.sync.strava;

import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.domain.activity.Activity;
import com.tpo.fitness.providers.api.service.ActivityRestClient;
import com.tpo.fitness.service.sync.Synchronizer;
import com.tpo.strava.persistence.service.repository.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Tiberiu
 * @since 04.10.17
 */
@Slf4j
@Service
public class AsyncSynchronizer implements Synchronizer {

    private final ActivityRestClient activityRestClient;
    private final ActivityRepository activityRepository;
    private final AtomicBoolean isInProgress;

    @Autowired
    public AsyncSynchronizer(ActivityRestClient activityRestClient,
                             ActivityRepository activityRepository) {
        this.activityRestClient = activityRestClient;
        this.activityRepository = activityRepository;
        isInProgress = new AtomicBoolean(false);
    }

    @Async
    public void sync(Athlete athlete) {
        if (isInProgress.get()) {
            log.info("Sync in progress, exiting!");
        } else {
            isInProgress.set(true);
            log.info("Starting sync athlete {} activities...", athlete.getId());
            Long lastStartDate = activityRepository.getLastStartDateByAthlete(athlete);

            List<Activity> activities = activityRestClient.getAllAfter(athlete, lastStartDate);

            log.info("Found {} new activities", activities.size());

            activities.stream()
                    .filter(activity -> !activityRepository.findByExternalId(activity.getExternalId()).isPresent())
                    .map(activity -> activityRestClient.getOne(athlete, activity.getExternalId()))
                    .forEach(this::persistActivity);

            log.info("Sync done!");
            isInProgress.set(false);
        }
    }

    private void persistActivity(Activity activity) {
        Activity newActivity = activityRepository.save(activity);
        log.info("Persisted activity {} with id {}", newActivity.getExternalId(), newActivity.getId());
    }
}
