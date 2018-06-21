package com.tpo.fitness.service.activity;

import com.tpo.fitness.domain.Sport;
import com.tpo.fitness.domain.activity.Activity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface ActivitiesService {

    List<Activity> findAll();

    List<Activity> findAllBySport(Sport sport);

    List<Activity> getAllInChronologicalOrder();

    List<Activity> findAllSinceTheLast(Duration duration);

    List<Activity> findAllBySportSince(Sport sport, LocalDateTime since);
}
