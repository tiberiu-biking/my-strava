package com.tpo.strava.persistence.service.repository.repository;

import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.domain.activity.Activity;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface ActivityRepository {

    Activity save(Activity activity);

    List<Activity> findAll();

    Optional<Activity> findByExternalId(String externalId);

    List<Activity> findAllSinceTheLast(Duration duration);

    List<Activity> findAllInChronologicalOrder();

    Activity findFirstByOrderByInsertDateDesc(Athlete athleteId);

    Long getLastStartDateByAthlete(Athlete athleteId);
}
