package com.tpo.fitness.service.activity;

import com.tpo.fitness.domain.activity.Activity;

import java.time.Duration;
import java.util.List;

public interface ActivitiesService {

    List<Activity> findAll();

    List<Activity> getAllInChronologicalOrder();

    List<Activity> findAllSinceTheLast(Duration duration);
}
