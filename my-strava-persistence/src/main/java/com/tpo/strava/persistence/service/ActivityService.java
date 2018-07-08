package com.tpo.strava.persistence.service;

import com.tpo.fitme.domain.Athlete;
import com.tpo.fitme.domain.Sport;
import com.tpo.fitme.domain.activity.Activity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface ActivityService {

    Activity save(Activity activity);

    List<Activity> findAll();

    List<Activity> findAllBySport(Sport sport);

    List<Activity> findAllBySportAndYear(Sport sport, int year);

    Optional<Activity> findByExternalId(String externalId);

    List<Activity> findAllForTheLast(Duration duration);

    List<Activity> findAllInChronologicalOrder();

    Activity findFirstByOrderByInsertDateDesc(Athlete athleteId);

    LocalDateTime getLastStartDateByAthlete(Athlete athleteId);
}
