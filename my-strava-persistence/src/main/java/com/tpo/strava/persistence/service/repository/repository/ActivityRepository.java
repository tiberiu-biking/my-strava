package com.tpo.strava.persistence.service.repository.repository;

import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.domain.activity.Activity;

import java.time.Duration;
import java.util.List;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface ActivityRepository {

    void save(Activity activity);

    List<Activity> findAll();

    List<Activity> findAllSinceTheLast(Duration duration);

    List<Activity> findAllInChronologicalOrder();

    Activity findFirstByOrderByInsertDateDesc(Athlete athleteId);

    Long getLastStartDateByAthlete(Athlete athleteId);
}
