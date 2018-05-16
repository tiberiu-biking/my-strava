package com.tpo.fitness.service.sync.strava;

import com.tpo.fitness.domain.activity.Activity;
import com.tpo.fitness.providers.api.service.ActivityRestClient;
import com.tpo.fitness.service.sync.Synchronizer;
import com.tpo.strava.persistence.service.repository.repository.ActivityDatabaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tiberiu
 * @since 04.10.17
 */
@Slf4j
@Service
public class StravaSynchronizer implements Synchronizer {

    private ActivityRestClient stravaActivityRestClient;
    private ActivityDatabaseRepository activityDatabaseRepository;

    @Autowired
    public StravaSynchronizer(ActivityRestClient stravaActivityRestClient,
                              ActivityDatabaseRepository activityDatabaseRepository) {
        this.stravaActivityRestClient = stravaActivityRestClient;
        this.activityDatabaseRepository = activityDatabaseRepository;
    }

    @Async
    @Scheduled(fixedRate = 600000)
    public void sync() {
        log.info("Starting sync...");

        Long lastStartDate = activityDatabaseRepository.getLastStartDate();

        List<Activity> activities = stravaActivityRestClient.findActivities(lastStartDate);

        log.info("Found {} new activities", activities.size());
        activities.forEach(this::persistActivity);

        log.info("Sync done!");
    }

    private void persistActivity(Activity activity) {
        activityDatabaseRepository.save(activity);
        log.debug("Persisted activity with id {}", activity.getId());
    }
}
