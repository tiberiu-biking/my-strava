package com.tpo.strava.persistence.service.repository.repository;

import com.tpo.fitness.domain.activity.Activity;

import java.util.List;
import java.util.Optional;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface ActivityRepository {

    void save(Activity activity);

    List<Activity> getAll();

    Activity findFirstByOrderByInsertDateDesc();

    Optional<Long> getLastStartDate();

}
