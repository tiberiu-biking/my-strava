package com.tpo.strava.service.sync;

import com.tpo.strava.persistence.service.repository.repository.ActivityDatabaseRepository;
import com.tpo.strava.service.activity.RemoteActivityService;
import com.tpo.strava.service.activity.StravaRemoteActivityService;
import com.tpo.strava.service.domain.activity.Activity;
import com.tpo.strava.service.properties.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tiberiu
 * @since 04.10.17
 */
@Service
public class StravaSynchronizer implements Synchronizer {

    private static final Logger logger = LoggerFactory.getLogger(StravaSynchronizer.class);

    private RemoteActivityService stravaRemoteActivityService;
    private AppProperties appProperties;
    private ActivityDatabaseRepository activityDatabaseRepository;

    @Autowired
    public StravaSynchronizer(StravaRemoteActivityService stravaActivityService, AppProperties appProperties, ActivityDatabaseRepository activityDatabaseRepository) {
        this.stravaRemoteActivityService = stravaActivityService;
        this.appProperties = appProperties;
        this.activityDatabaseRepository = activityDatabaseRepository;
    }

    @Async
    @Scheduled(fixedRate = 30000)
    public void sync() {
        logger.info("Starting sync...");
        long lastStartDate = activityDatabaseRepository.getLastStartDate();
        List<Activity> activities = stravaRemoteActivityService.findActivities(lastStartDate);
        logger.info("Found {} new activities", activities.size());
        activities.forEach(this::persistActivity);
        logger.info("Sync done!");

    }

    private void persistActivity(Activity activity) {
        activityDatabaseRepository.save(activity);
        logger.info("Persisted activity with id {}", activity.getId());
    }
}
