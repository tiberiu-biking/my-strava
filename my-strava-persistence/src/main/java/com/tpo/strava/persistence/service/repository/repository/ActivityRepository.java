package com.tpo.strava.persistence.service.repository.repository;

import com.tpo.fitness.domain.activity.Activity;

import java.util.List;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface ActivityRepository {

    void save(Activity activity);

    List<Activity> getAll();

    List<Activity> getAllInChronologicalOrder();

    Activity findFirstByOrderByInsertDateDesc();

    Long getLastStartDate();
}
