package com.tpo.strava.persistence.service.repository.repository;

import com.tpo.strava.service.domain.activity.Activity;

import java.util.List;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface ActivityRepository {

    void save(Activity activity);

    List<Activity> getAll();

    Activity findFirstByOrderByInsertDateDesc();

    long getLastStartDate();
}
