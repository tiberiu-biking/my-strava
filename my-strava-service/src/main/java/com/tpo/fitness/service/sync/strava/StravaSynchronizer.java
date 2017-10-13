package com.tpo.fitness.service.sync.strava;

import com.tpo.fitness.domain.activity.Activity;
import com.tpo.fitness.providers.api.service.ActivityRestClient;
import com.tpo.fitness.service.sync.Synchronizer;
import com.tpo.strava.persistence.service.repository.repository.ActivityDatabaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tiberiu
 * @since 04.10.17
 */
@Service
public class StravaSynchronizer implements Synchronizer {

    private static final Logger logger = LoggerFactory.getLogger(StravaSynchronizer.class);

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
        logger.info("Starting sync...");

        Optional<Long> lastStartDate = activityDatabaseRepository.getLastStartDate();
        List<Activity> activities;
        activities = lastStartDate
                .map(aLong -> stravaActivityRestClient.findActivities(aLong))
                .orElseGet(() -> stravaActivityRestClient.getAllActivities());

        logger.info("Found {} new activities", activities.size());
        activities.forEach(this::persistActivity);

        logger.info("Sync done!");
    }

    private void persistActivity(Activity activity) {
        activityDatabaseRepository.save(activity);
        logger.debug("Persisted activity with id {}", activity.getId());
    }
}
