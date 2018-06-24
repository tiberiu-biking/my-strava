package com.tpo.fitness.service.activity;

import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.domain.activity.Activity;

import java.time.Duration;
import java.util.List;

public interface ActivitiesService {

    List<Activity> findAll();

    List<Activity> findAllBySport(Sport sport);

    List<Activity> getAllInChronologicalOrder();

    List<Activity> findAllSinceTheLast(Duration duration);

    List<Activity> findAllBySportAndYear(Sport sport, int year);
}
