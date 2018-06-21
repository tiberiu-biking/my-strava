package com.tpo.strava.persistence.service.repository.repository;

import com.tpo.fitness.domain.Athlete;
import com.tpo.fitness.domain.Sport;
import com.tpo.fitness.domain.activity.Activity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface ActivityRepository {

    Activity save(Activity activity);

    List<Activity> findAll();

    List<Activity> findBySport(Sport sport);

    List<Activity> findBySportAndStartDateAfter(Sport sport, LocalDateTime date);

    Optional<Activity> findByExternalId(String externalId);

    List<Activity> findAllForTheLast(Duration duration);

    List<Activity> findAllInChronologicalOrder();

    Activity findFirstByOrderByInsertDateDesc(Athlete athleteId);

    LocalDateTime getLastStartDateByAthlete(Athlete athleteId);
}
