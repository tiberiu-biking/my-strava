package com.tpo.fitness.service.activity;

import com.tpo.fitness.domain.ActivitiesSummary;
import com.tpo.fitness.domain.activity.Activity;

import java.util.List;

public interface ActivitiesService {

    List<Activity> getAll();

    List<Activity> getAllInChronologicalOrder();

    List<ActivitiesSummary> getSummary();
}
