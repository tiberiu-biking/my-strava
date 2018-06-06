package com.tpo.fitness.service.activity;

import com.tpo.fitness.domain.activity.Activity;
import com.tpo.strava.persistence.service.repository.repository.ActivityDatabaseRepository;
import com.tpo.strava.persistence.service.repository.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

/**
 * Created by Tiberiu on 25/10/15.
 */
@Slf4j
@Service
public class UIActivitiesService implements ActivitiesService {

    private ActivityRepository activityRepository;

    @Autowired
    public UIActivitiesService(ActivityDatabaseRepository activityDatabaseRepository) {
        this.activityRepository = activityDatabaseRepository;
    }

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public List<Activity> getAllInChronologicalOrder() {
        return activityRepository.findAllInChronologicalOrder();
    }

    @Override
    public List<Activity> findAllSinceTheLast(Duration duration) {
        return activityRepository.findAllSinceTheLast(duration);
    }
}
