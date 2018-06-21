package com.tpo.fitness.service.activity;

import com.tpo.fitness.domain.Sport;
import com.tpo.fitness.domain.activity.Activity;
import com.tpo.strava.persistence.service.repository.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Tiberiu on 25/10/15.
 */
@Slf4j
@Service
public class DefaultActivitiesService implements ActivitiesService {

    private final ActivityRepository activityRepository;

    @Autowired
    public DefaultActivitiesService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public List<Activity> findAllBySport(Sport sport) {
        return activityRepository.findBySport(sport);
    }

    @Override
    public List<Activity> findAllBySportSince(Sport sport, LocalDateTime date) {
        return activityRepository.findBySportAndStartDateAfter(sport, date);
    }

    @Override
    public List<Activity> getAllInChronologicalOrder() {
        return activityRepository.findAllInChronologicalOrder();
    }

    @Override
    public List<Activity> findAllSinceTheLast(Duration duration) {
        return activityRepository.findAllForTheLast(duration);
    }

}
